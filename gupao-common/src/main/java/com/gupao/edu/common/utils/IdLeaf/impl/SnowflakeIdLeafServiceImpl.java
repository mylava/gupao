package com.gupao.edu.common.utils.IdLeaf.impl;

import com.gupao.edu.common.utils.IdLeaf.IdLeafService;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

/**雪花算法分号器实现
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-17 20:48
 */
@Service
public class SnowflakeIdLeafServiceImpl implements IdLeafService {
    private static final long SEQUENCE_BITS = 12L;//序列码长度
    private static final long MACHINE_ID_BITS = 10L;//机器id长度
    private static final long MACHINE_ID_SHIFT = SEQUENCE_BITS;//机器id左位移数
    private static final long TIMESTAMP_LEFT_SHIFT = MACHINE_ID_BITS + SEQUENCE_BITS;//时间戳左位移数
    private static final long TWEPOCH = 1517414400000L;//2018-02-01 00:00:00时间戳
    private static final long MAX_MACHINE_ID = ~(-1L << MACHINE_ID_BITS);//机器id最大值1023
    private static final long MAX_SEQUENCE = 4095;//2**12 单位毫秒最大序列值

    private volatile long lastTimestamp = -1L;
    private volatile AtomicLong sequence = new AtomicLong(0);

    private long machineId;

    public SnowflakeIdLeafServiceImpl() {
        machineId = getMachineId();
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new RuntimeException("machineId > MaxMachineId");
        }
    }

    @Override
    public long getOrderNo() {
        return nextId();
    }

    private long getMachineId() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            long id= 1;
            if (network == null) {
                return id;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null==mac) {
                    return id;
                }
                id = ((0x000000FF & (long) mac[mac.length - 1]) |
                        (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            }
            return id;
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException("获取机器id失败", e);
        }
    }

    private long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        }

        if (lastTimestamp == timestamp) {
            sequence.set((sequence.get() + 1) & MAX_SEQUENCE);
            if (sequence.get() == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence.set(0L);
        }

        lastTimestamp = timestamp;
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT) | (machineId << MACHINE_ID_SHIFT) | sequence.get();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
