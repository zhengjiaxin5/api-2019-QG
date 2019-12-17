package com.edu.httpclient_demo;

import java.io.IOException;

import org.apache.http.client.CookieStore;
import org.testng.annotations.Test;

import com.edu.core.HttpDriver;
import com.edu.utils.Checker;
import com.edu.utils.Common;
import com.edu.utils.ReadPro;

import net.sf.json.JSONObject;

/*
 * ×÷Õß£ºÖ£¼ÎÜ°
 * */

public class Submit {
	//String submit_url="/fgadmin/orders/submit";
	@Test
	public void Testsubmit() throws IOException, Exception {
		String url=ReadPro.getPropValue("base_url")+ReadPro.getPropValue("submit_url");
		 CookieStore cookie=Common.getCookie("20000000000", "netease123");
		 JSONObject para=new JSONObject();
		 para.element("skuIds","2,3");
		 para.element("skuNumbers","5,5");
		 para.element("stockIds","74966312,74966313");
		 para.element("receiverName","55");
		 para.element("cellPhone","18103217634");
		 para.element("addressDetail","25");
		 para.element("province","52");
		 para.element("city","25");
		 para.element("area","22");
		 String result=HttpDriver.doPost(url, para, cookie);
		 System.out.println(result);
		 Checker checker=new Checker(result);
		 checker.verifyXpath("message", "success");
	}
}


