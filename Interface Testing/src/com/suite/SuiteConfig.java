
package com.suite;
import org.testng.annotations.*;

public class SuiteConfig {
	
	@BeforeSuite
	public void befSui()
	{
		System.out.println("Before Suite ����");
	}
	
	@AfterSuite
	public void aftSui()
	{
		System.out.println("After Suite ����");
	}
	
	@BeforeTest
	public void befTest()
	{
		System.out.println("Before Test ����");
	}
	
	@AfterTest
	public void AftTest()
	{
		System.out.println("After Test ����");
	}
}
