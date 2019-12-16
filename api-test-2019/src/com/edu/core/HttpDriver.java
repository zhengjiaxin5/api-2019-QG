package com.edu.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.edu.utils.ReadPro;

import net.sf.json.JSONObject;

public class HttpDriver {
	static CloseableHttpClient httpClient = null;
	static CloseableHttpResponse respone = null;

	public static String doGet(String url) throws Exception {
		httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(ReadPro.getPropValue("base_url") + url);
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;
	}

	public static String doGet(String url, JSONObject data) throws Exception {
		String para = URLEncoder.encode(data.toString(), "UTF-8");
		httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url + "?" + para);
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;
	}

	public static String doGet(String url, String para) throws Exception {
		httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(ReadPro.getPropValue("base_url") + url + "?" + para);
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;
	}

	// 把map类型转换为String,并用&加以拼接
	public static String mapToString(Map<String, Object> para) {

		StringBuilder sBuilder = new StringBuilder();
		String content = null;
		int size = para.size();
		for (Entry<String, Object> entry : para.entrySet()) {
			sBuilder.append(entry.getKey() + "=" + entry.getValue());
			size--;
			if (size >= 1) {
				sBuilder.append("&");
			}

		}
		return sBuilder.toString();
	}

	public static String doGet(String url, Map<String, Object> para) throws ParseException, IOException {
		String content = null;
		httpClient = HttpClients.createDefault();

		HttpGet get = new HttpGet(ReadPro.getPropValue("base_url") + url + "?" + mapToString(para));
		respone = httpClient.execute(get);
		HttpEntity entity = respone.getEntity();
		content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;
	}

	public static String doPost(String url, JSONObject para) {
		httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadPro.getPropValue("base_url") + url);
		post.addHeader("Content-Type", "application/json");
		HttpEntity data;
		String content = null;
		try {
			data = new StringEntity(para.toString());

			post.setEntity(data);
			respone = httpClient.execute(post);

			HttpEntity entity = respone.getEntity();
			content = EntityUtils.toString(entity, "utf-8");
			EntityUtils.consume(entity);

			respone.close();
			httpClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;

	}

	public static String doGet(String url, CookieStore cookie) throws Exception {

		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie)
				.build();
		HttpGet get = new HttpGet(ReadPro.getPropValue("base_url") + url);
		CloseableHttpResponse response = client.execute(get);
		HttpEntity result_entity = response.getEntity();
		String result = EntityUtils.toString(result_entity, "utf-8");

		EntityUtils.consume(result_entity);
		response.close();
		client.close();
		return result;
	}

	public static String doPostByForm(String url, Map<String, Object> para) throws Exception {
		httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadPro.getPropValue("base_url") + url);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity data;
		String content = null;

		data = new StringEntity(mapToString(para), "utf-8");
		post.setEntity(data);
		respone = httpClient.execute(post);

		HttpEntity entity = respone.getEntity();
		content = EntityUtils.toString(entity, "utf-8");

		respone.close();
		httpClient.close();

		return content;

	}

	public static String doPostByForm(String url, String para) throws Exception {
		httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(ReadPro.getPropValue("BaseUrl") + url);
		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
		HttpEntity data;
		String content = null;

		data = new StringEntity(para);

		post.setEntity(data);
		respone = httpClient.execute(post);

		HttpEntity entity = respone.getEntity();
		content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();

		return content;

	}

	public static String doPost(String url, JSONObject para, CookieStore cookie) throws Exception {
		RequestConfig gConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpClient = HttpClients.custom().setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();

		HttpPost post = new HttpPost(url);
		post.addHeader("Content-Type", "application/json");
		HttpEntity data = new StringEntity(para.toString());
		post.setEntity(data);
		CloseableHttpResponse respone = httpClient.execute(post);

		HttpEntity entity = respone.getEntity();
		String content = EntityUtils.toString(entity, "utf-8");
		EntityUtils.consume(entity);
		respone.close();
		httpClient.close();
		return content;

	}

}
