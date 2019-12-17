package com.edu.httpclient_demo;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;

import net.sf.json.JSONObject;

/*
 * „∆–¶«ß
 * */

public class LoginTest {
	HttpDriver dr=new HttpDriver();
	@Test
	public void test1() throws ClientProtocolException, IOException {
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000000");
		user.element("password", "netease123");
		String c = dr.doPost("/login", user);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}
	@Test
	public void test2() throws ClientProtocolException, IOException {
		JSONObject user = new JSONObject();
		user.element("phoneArea", "84");
		user.element("phoneNumber", "20000000000");
		user.element("password", "netease123");
		String c = dr.doPost("/common/fgadmin/login", user);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}
	@Test
	public void test3() throws ClientProtocolException, IOException {
		JSONObject user = new JSONObject();
		user.element("phoneArea", "86");
		user.element("phoneNumber", "20000000");
		user.element("password", "netease123");
		String c = dr.doPost("/common/fgadmin/login", user);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}

	
	

}

