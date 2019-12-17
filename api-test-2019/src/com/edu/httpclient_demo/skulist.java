package com.webtest.httpclient_demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;



/*
 * 作者：鲍宣杞
 * */

public class skulist {
	String skuList_url="/common/skulist";
	String skulistByid_url="/common/skulistbyid";
	@Test(description="获取skulist")
	public void testSkuList() throws IOException, Exception {
		String result=HttpDriver.doGet(skuList_url);
		Checker check=new Checker(result);
		try {
			check.assertXpath("message", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(description="获取skulist带正确的参数id")
	public void testSkuListByid1() throws IOException, Exception {
		String result=HttpDriver.doGet(skulistByid_url,"goodsId=1");
		Checker check=new Checker(result);
		try {
			check.assertXpath("message", "success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(description="获取skulist带错误的参数id")
	public void testSkuListByid2() throws IOException, Exception {
		String result=HttpDriver.doGet(skulistByid_url,"goodsId=2");
		Checker check=new Checker(result);
		try {
			check.assertXpath("message", "商品ID不存在");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

