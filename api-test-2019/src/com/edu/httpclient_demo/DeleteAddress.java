package com.edu.httpclient_demo;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.BaseTest;
import com.edu.core.HttpDriver;
import com.edu.utils.Checker;
import com.edu.utils.Common;

import net.sf.json.JSONObject;

/*
 * 作者：马寅祥
 * */
public class DeleteAddress{
	Checker check=null;
	public void getSubmitBtpara(JSONObject user,CookieStore cookie) throws Exception {
		String res=HttpDriver.doPost("/fgadmin/address/delete",user,cookie);
		
		System.out.println(res);
		check=new Checker(res);
	}
	@Test
	public void test1() throws Exception {
		CookieStore cookie= Common.getcookie("20000000001", "netease123");
		JSONObject user=new JSONObject();
		user.element("id", "77243286");
		getSubmitBtpara(user,cookie);
		check.verifyXpath("code", "200");
	}
	@Test
	public void test2() throws Exception {
		CookieStore cookie= Common.getcookie("20000000001", "netease123");
		JSONObject user=new JSONObject();
		getSubmitBtpara(user,cookie);
		check.verifyXpath("code", "200");
	}
}