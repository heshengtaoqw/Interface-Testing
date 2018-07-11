package com.ExpectException;

import org.testng.annotations.Test;

public class ExpectException {
	
	@Test(expectedExceptions = RuntimeException.class)
	public void ExpectException()
	{
		System.out.println("this is a runTimeException");
		throw new RuntimeException();
	}
}
