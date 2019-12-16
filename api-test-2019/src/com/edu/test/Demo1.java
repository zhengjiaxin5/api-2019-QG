package com.edu.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.edu.core.ApiListener;

@Listeners(ApiListener.class)
public class Demo1 {
	@Test
	public void t1() {
		System.out.println("hello");
	}

}
