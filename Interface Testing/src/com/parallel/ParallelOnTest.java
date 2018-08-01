package com.parallel;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class ParallelOnTest {
	
	@Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
	public void threadTest4()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 100 and id is " + id);
	}
	
}
