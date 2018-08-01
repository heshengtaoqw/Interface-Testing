package com.suite;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class LoginTest {

	@Test
	public void Login()
	{
		System.out.println("Login Successfully");
	}
	
	@Test(enabled = false)
	public void LoginIgnore()
	{
		System.out.println("Ignore test");
	}
}
