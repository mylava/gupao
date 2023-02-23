package com.gupao.edu.order.server.utils.logistics;

import com.gupao.edu.common.utils.Md5Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: gupao-parent
 * @description: 查询物流信息 util
 * @Author: <a href="568227120@qq.com">heliang.wang</a>
 * @Date: 2020/3/29 1:54 下午
 * @Version: 1.0
 */
public class LogisticsUtil {

	/**
	* 快递 100 查询地址
	*/
	private static final String SYNQUERY_URL = "http://poll.kuaidi100.com/poll/query.do";

	/**
	 * 实时查询快递单号
	 * @param key			贵司的授权key
	 * @param customer		贵司的查询公司编号
	 * @param com			快递公司编码
	 * @param num			快递单号
	 * @param phone			手机号
	 * @param from			出发地城市
	 * @param to			目的地城市
	 * @param resultv2		开通区域解析功能：0-关闭；1-开通
	 * @return
	 */
	public static String synQueryData(String key,String customer,String com, String num, String phone, String from, String to, int resultv2) {

		StringBuilder param = new StringBuilder("{");
		param.append("\"com\":\"").append(com).append("\"");
		param.append(",\"num\":\"").append(num).append("\"");
		param.append(",\"phone\":\"").append(phone).append("\"");
		param.append(",\"from\":\"").append(from).append("\"");
		param.append(",\"to\":\"").append(to).append("\"");
		if(1 == resultv2) {
			param.append(",\"resultv2\":1");
		} else {
			param.append(",\"resultv2\":0");
		}
		param.append("}");

		Map<String, String> params = new HashMap<String, String>();
		params.put("customer", customer);
		String sign = Md5Utils.MD5Encode(param + key + customer);
		params.put("sign", sign);
		params.put("param", param.toString());

		return post(params);
	}

	/**
	*  @Decsription: 发送请求，查询物流信息
	*  @Param params
	*  @Author: <a href="568227120@qq.com">heliang.wang</a>
	*  @Date: 2020/3/29 2:05 下午
	*  @return: java.lang.String
	*/
	private static String post(Map<String, String> params) {
		StringBuffer response = new StringBuffer("");

		BufferedReader reader = null;
		try {
			StringBuilder builder = new StringBuilder();
			for (Map.Entry<String, String> param : params.entrySet()) {
				if (builder.length() > 0) {
					builder.append('&');
				}
				builder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				builder.append('=');
				builder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] bytes = builder.toString().getBytes("UTF-8");

			URL url = new URL(SYNQUERY_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(bytes.length));
			conn.setDoOutput(true);
			conn.getOutputStream().write(bytes);

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response.toString();
	}
}
