package com.interfaceTesting;
import org.testng.annotations.*;

public class BasicAnnotation {
	
	@Test
	public void testMethod()
	{System.out.println("test");}
	
	@Test
	public void testMethod2()
	{System.out.println("test2");}
	
	@BeforeMethod
	public void testBefore()
	{System.out.println("test before");}
	
	@AfterMethod
	public void testAfter()
	{
		System.out.println("test after");
	}
	
	@BeforeClass
	public void testBefCla()
	{System.out.println("Before Class");}
	
	@AfterClass
	public void testAftCla()
	{
		System.out.println("After Class");
	}
	
	@BeforeSuite
	public void testBefSui()
	{
		System.out.println("Before Suite");
	}
	
	@AfterSuite
	public void testAftSui()
	{
		System.out.println("After Suite");
	}
	

}
