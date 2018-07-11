package com.dependOn;
import org.testng.annotations.*;

public class DependOn {
	
	@Test
	public void testDep()
	{
		System.out.println("this is A test");
		throw new RuntimeException();
	}
	
	@Test(dependsOnMethods = "testDep")
	public void testDep2()
	{
		System.out.println("this is B test, needed to depend On A test");
	}

}
