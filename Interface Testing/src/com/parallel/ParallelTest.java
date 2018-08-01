package com.parallel;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.*;

public class ParallelTest {
	/*
    @BeforeMethod
    public void beforeMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-method. Thread id is: " + id);
    }
	*/
	
	@Test
	public void threadTest1()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 1 and id is " + id);
	}
	
	@Test
	public void threadTest2()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 2 and id is " + id);
	}
	
	@Test
	public void threadTest3()
	{
		long id = Thread.currentThread().getId();
		System.out.println("this is thread 3 and id is " + id);
	}
	
	/*    @AfterMethod
    public void afterMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-method. Thread id is: " + id);
    }*/

}
