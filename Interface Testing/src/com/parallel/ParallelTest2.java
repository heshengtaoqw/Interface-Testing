package com.parallel;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class ParallelTest2 {
	
	
	@Test
	public void threadTest4()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 4 and id is " + id);
	}
	
	@Test
	public void threadTest5()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 5 and id is " + id);
	}
	
	@Test
	public void threadTest6()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 6 and id is " + id);
	}

}
