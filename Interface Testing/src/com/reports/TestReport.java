package com.reports;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestReport {

	@Test
	public void test1()
	{
		System.out.println("test1");
	}
	
	@Test
	public void test2()
	{
		System.out.println("test2");
	}
	
	@Test
	public void test3()
	{
		System.out.println("test3");
	}
	
	@Test
	public void testReport()
	{
		Reporter.log("this is my reporter log");
		throw new RuntimeException("this is my runtime exception");
	}
}
