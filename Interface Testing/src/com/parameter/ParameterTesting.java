package com.parameter;
import org.testng.annotations.*;

public class ParameterTesting {
	
	@Test
	@Parameters({"name","age"})
	public void testParam(String name, int age)
	{
		System.out.println("name is " + name + ", age is " + age);
	}
}
