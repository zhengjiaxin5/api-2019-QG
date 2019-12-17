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
 * 作者：耿一凡
 * */
public class NewAddressTest {
	Common Common=new Common();
	HttpDriver dr=new HttpDriver();
	@Test
	public void testSuccess() throws Exception {
		CookieStore cookie= Common.getCookie("20000000000", "netease123");
		JSONObject user = new JSONObject();
		user.element("receiverName", "张三");
		user.element("cellPhone", "12345678901");
		user.element("addressDetail", "浙江大学");
		user.element("province", "浙江省");
		user.element("city", "杭州市");
		user.element("area", "滨江区");
		String c = HttpDriver.doPost("/fgadmin/address/new", user,cookie);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}
	
	@Test
	public void testFailID() throws Exception {
		CookieStore cookie= Common.getCookie("20000000000", "netease123");
		JSONObject user = new JSONObject();
		user.element("id", "1");
		user.element("receiverName", "张三");
		user.element("cellPhone", "12345678901");
		user.element("addressDetail", "浙江大学");
		user.element("province", "浙江省");
		user.element("city", "杭州市");
		user.element("area", "滨江区");
		String c = dr.doPost("/fgadmin/address/new", user,cookie);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}
	
	@Test
	public void testFailOtherNull() throws Exception {
		CookieStore cookie= Common.getCookie("20000000000", "netease123");
		JSONObject user = new JSONObject();
		user.element("receiverName", "张三");
		user.element("addressDetail", "浙江大学");
		user.element("province", "571");
		user.element("city", "杭州市");
		user.element("area", "滨江区");
		String c = dr.doPost("/fgadmin/address/new", user,cookie);
		JSONObject jsonResult=JSONObject.fromObject(c);
		assertEquals(jsonResult.getString("message"), "success");
	}

}