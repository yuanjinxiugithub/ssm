package com.ssm.base.util;

/**
 * @author yjx
 * 调用http请求的工具类
 */
public class HttpAdaptorUtil {
	
	public static String send(String url) {
		try {
			/*CloseableHttpClient client = HttpClients.createDefault();
			HttpPost method = new HttpPost(url);
			CloseableHttpResponse response = client.execute(method);
			try {
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = response.getEntity();
					String resp = EntityUtils.toString(httpEntity);
					Message message = JSON.parseObject(resp, Message.class);
					return message;
				} else {
					logger.info("HTTP ERROR Status: " + response.getStatusLine().getStatusCode() + ":" + response.getStatusLine().getReasonPhrase());
					throw new RuntimeException("HTTP ERROR Status: " + response.getStatusLine().getStatusCode() + ":" + response.getStatusLine().getReasonPhrase());
				}
			} finally {
				response.close();
			}*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
