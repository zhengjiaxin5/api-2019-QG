package com.edu.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class Common {
	
	public static CookieStore getCookie(String u_name,String password) throws Exception, IOException {
		JSONObject user=new JSONObject();
		user.element("phoneArea", "86");
		user.put("phoneNumber", u_name);
		user.element("password", password);
		
		//创建带自定义Cookie的Client
		RequestConfig gConfig =
				RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		CookieStore cookie=new BasicCookieStore();
		CloseableHttpClient client=HttpClients.custom().
				setDefaultRequestConfig(gConfig).setDefaultCookieStore(cookie).build();
		
		String loginurl=ReadPro.getPropValue("base_url")+"/common/fgadmin/login";
		HttpPost post =new HttpPost(loginurl);
		//设置请求体
		HttpEntity userEntity=new StringEntity(user.toString());
		post.setHeader("Content-Type","application/json");
		post.setEntity(userEntity);
		CloseableHttpResponse response=client.execute(post);
		HttpEntity responseEntity=response.getEntity();
		String result=EntityUtils.toString(responseEntity,"UTF-8");
		EntityUtils.consume(responseEntity);
		response.close();
		client.close();
	
		return cookie;
		
		
	}

}
