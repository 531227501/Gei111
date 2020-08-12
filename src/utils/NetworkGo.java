package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkGo {

	private final static String separator = System.getProperty("line.separator");


	public NetworkGo() {


	}

	public static String get(String urlPath) {
		/*
		* User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36
		* */

		String result = "";
		HttpURLConnection httpURLConnection = null;
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null;
		InputStream inputStream = null;

		try {
			URL url = new URL(urlPath);
			httpURLConnection = (HttpURLConnection) url.openConnection();

			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36)");

			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setConnectTimeout(1000);
			httpURLConnection.setReadTimeout(2000);
			httpURLConnection.setInstanceFollowRedirects(false);
			httpURLConnection.connect();

			int responseCode = httpURLConnection.getResponseCode();
			String responseMessage = httpURLConnection.getResponseMessage();

			System.out.println("responseCode: " + responseCode);
			System.out.println("responseMessage: " + responseMessage);

			//            if (200 == responseCode) {
			if (responseCode == 200) {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputStreamReader);
				StringBuilder stringBuffer = new StringBuilder();

				String temp;
				while ((temp = bufferedReader.readLine()) != null) {
					stringBuffer.append(temp);
					stringBuffer.append(separator);
				}

				result = stringBuffer.toString();

			} else if (responseCode == 403) {
				httpURLConnection.getHeaderField("");
				inputStream = httpURLConnection.getErrorStream();
				inputStreamReader = new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputStreamReader);
				StringBuilder stringBuffer = new StringBuilder();

				String temp;
				while ((temp = bufferedReader.readLine()) != null) {
					stringBuffer.append(temp);
					stringBuffer.append(separator);
				}

				result = stringBuffer.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}

		return result;
	}


	public static String post(String httpUrl, String param) {

		HttpURLConnection connection = null;
		InputStream is = null;
		OutputStream os = null;
		BufferedReader br = null;
		String result = null;
		try {
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开连接
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接请求方式
			connection.setRequestMethod("POST");
			// 设置连接主机服务器超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取主机服务器返回数据超时时间：60000毫秒
			connection.setReadTimeout(60000);

			// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
			connection.setDoOutput(true);
			// 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
			connection.setDoInput(true);
			// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
			connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
			// 通过连接对象获取一个输出流
			os = connection.getOutputStream();
			// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
			os.write(param.getBytes());
			// 通过连接对象获取一个输入流，向远程读取
			if (connection.getResponseCode() == 200) {

				is = connection.getInputStream();
				// 对输入流对象进行包装:charset根据工作项目组的要求来设置
				br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				StringBuffer sbf = new StringBuffer();
				String temp = null;
				// 循环遍历一行一行读取数据
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
					sbf.append("\r\n");
				}
				result = sbf.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 断开与远程地址url的连接
			connection.disconnect();
		}
		return result;
	}
}
