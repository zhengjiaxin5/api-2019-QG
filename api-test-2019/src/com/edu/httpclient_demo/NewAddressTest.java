package com.edu.httpclient_demo;


import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Common;

import net.sf.json.JSONObject;


/*
 * ���ߣ���һ��
 * */
public class NewAddressTest {
	HttpDriver dr=new HttpDriver();
	@Test
	public void testSuccess() throws ClientProtocolException, IOException {
		CookieStore cookie = Common.getCookie();
		JSONObject user = new JSONObject();
		user.element("receiverName", "����");
		user.element("cellPhone", "12345678901");
		user.element("addressDetail", "�㽭��ѧ");
		user.element("province", "�㽭ʡ");
		user.element("city", "������");
		user.element("area", "������");
		String c = HttpDriver.doPost("/fgadmin/address/new", user,cookie);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}
	
	@Test
	public void testFailID() throws ClientProtocolException, IOException {
		CookieStore cookie = Common.getCookie();
		JSONObject user = new JSONObject();
		user.element("id", "1");
		user.element("receiverName", "����");
		user.element("cellPhone", "12345678901");
		user.element("addressDetail", "�㽭��ѧ");
		user.element("province", "�㽭ʡ");
		user.element("city", "������");
		user.element("area", "������");
		String c = dr.doPost("/fgadmin/address/new", user,cookie);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}
	
	@Test
	public void testFailOtherNull() throws ClientProtocolException, IOException {
		CookieStore cookie = Common.getCookie();
		JSONObject user = new JSONObject();
		user.element("receiverName", "����");
		user.element("addressDetail", "�㽭��ѧ");
		user.element("province", "571");
		user.element("city", "������");
		user.element("area", "������");
		String c = dr.doPost("/fgadmin/address/new", user,cookie);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}

}