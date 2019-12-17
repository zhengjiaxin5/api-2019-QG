package com.edu.httpclient_demo;

import static org.testng.Assert.assertEquals;



import java.io.IOException;

import javax.mail.internet.NewsAddress;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.testng.annotations.Test;

import com.edu.utils.*;

import net.sf.json.JSONObject;

import com.edu.core.*;

/*
 * ���ߣ�������
 * */

public class Addresslist {
//	CookieStore cookieStore=new BasicCookieStore();
	String url="/fgadmin/address/list";
	String url2="/fgadmin/address/new";
	@Test(description="��ַΪ��ʱ",priority=1)
	public void nulladdress() throws IOException, Exception {
		CookieStore cookieStore=CookieDemo.getcookie("20000000000", "netease123");
		System.out.println(cookieStore);
		String result=HttpDriver.doGet(url, cookieStore);
		System.out.println(result);
		JSONObject jsonResult=JSONObject.fromObject(result);
		assertEquals(jsonResult.getString("message"), "�ջ��ַΪ��");
		assertEquals(jsonResult.getInt("code"), 201);
		
	}
	@Test(priority=2)
	public void add1() throws Exception {
		CookieStore cookieStore=CookieDemo.getcookie("20000000000", "netease123");
		System.out.println(cookieStore);
		JSONObject user=new JSONObject();
		user.element("receiverName", "����");
		user.element("cellPhone", "12345678901");
		user.element("addressDetail", "�㽭��ѧ");
		user.element("province", "�㽭ʡ");
		user.element("city", "������");
		user.element("area", "������");
		String result=HttpDriver.doPost(url2, user, cookieStore);
		System.out.println(result);
		JSONObject jsonResult=JSONObject.fromObject(result);
		assertEquals(jsonResult.getString("message"), "success");
		assertEquals(jsonResult.getInt("code"), 200);
		
	}
	@Test(description="��ַ��Ϊ��",priority=3)
	public void addresslist() throws IOException, Exception {
		CookieStore cookieStore=CookieDemo.getcookie("20000000000", "netease123");
		System.out.println(cookieStore);
		String result=HttpDriver.doGet(url, cookieStore);
		System.out.println(result);
		JSONObject jsonResult=JSONObject.fromObject(result);
		assertEquals(jsonResult.getString("message"), "success");
		assertEquals(jsonResult.getInt("code"), 200);
	}
	
}
