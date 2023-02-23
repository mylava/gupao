package com.gupao.edu.order.server.aspect;

import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.LockPrefix;
import com.gupao.edu.common.result.CodeMessage;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.order.server.annotation.NoRepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**处理订单重复提交
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-03 11:32
 */
@Aspect
@Component
@Slf4j
public class OrderDuplicateSubmitAspect {


    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around("pointCut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {

        int lockSeconds = noRepeatSubmit.lockTime();
        HttpServletRequest request = getRequest();

        String token = request.getHeader("Authorization");
        String path = request.getServletPath();
        String key = token+path;
        String clientId = UUID.randomUUID().toString();
        boolean isSuccess = CacheUtil.lock(LockPrefix.LOCK_ORDER, key, clientId, lockSeconds);

        if (isSuccess) {
            log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
            // 获取锁成功
            Object result = null;
            try {
                //执行进程
                result = pjp.proceed();
            }
            catch (Exception e) {
                log.error("", e);
            }finally {
                // 解锁
                CacheUtil.unLock(LockPrefix.LOCK_ORDER, key, clientId);
                log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
            }
            return result;
        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("tryLock fail, key = [{}]", key);
            return Result.fail(CodeMessage.ORDER_NOT_REPEAT);
        }

    }


    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ra.getRequest();
    }

}
