package com.timeOut;

import org.testng.annotations.*;


public class TimeOut {

	@Test(timeOut = 2000)
	public void testTimeOut() throws InterruptedException 
	{
		Thread.sleep(1000);
		System.out.println("this is test TimeOut");
	}
}
