package com.edu.httpclient_demo;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.edu.core.HttpDriver;

import net.sf.json.JSONObject;
/*
 * ���ߣ�����ˮ
 * */

public class Transportfee {
	public static String url="/common/getTransportFee";
	@Test
	public static void Transfee1() throws Exception {
		Map<String, Object> para=new HashMap<String, Object>();
		para.put("id", "1");
		para.put("addressDetail", "�ӱ�ʦ����ѧ");
		String result=HttpDriver.doGet(url,para);
		System.out.println(result);
		JSONObject jsonResult=JSONObject.fromObject(result);
		assertEquals(jsonResult.getString("message"), "success");
		assertEquals(jsonResult.getInt("code"), 200);
		assertEquals(jsonResult.getInt("result"),6);
	}
	@Test
	public static void Transfee2() throws Exception {
		Map<String, Object> para=new HashMap<String, Object>();
		para.put("id", "aaa");
		para.put("addressDetail", "�ӱ�ʦ����ѧ");
		String result=HttpDriver.doGet(url,para);
		System.out.println(result);
		JSONObject jsonResult=JSONObject.fromObject(result);
		assertEquals(jsonResult.getString("message"), "����ʧ��");
		assertEquals(jsonResult.getInt("code"), 400);
	}
}