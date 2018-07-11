
package com.suite;
import org.testng.annotations.*;

public class SuiteConfig {
	
	@BeforeSuite
	public void befSui()
	{
		System.out.println("Before Suite 运行");
	}
	
	@AfterSuite
	public void aftSui()
	{
		System.out.println("After Suite 运行");
	}
	
	@BeforeTest
	public void befTest()
	{
		System.out.println("Before Test 运行");
	}
	
	@AfterTest
	public void AftTest()
	{
		System.out.println("After Test 运行");
	}
}
