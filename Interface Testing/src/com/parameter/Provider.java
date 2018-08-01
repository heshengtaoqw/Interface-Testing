package com.parameter;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import org.testng.annotations.*;

public class Provider {
	
	@Test(dataProvider = "data")
	public void TestDataProvider(String name, int age, String sex)
	{
		System.out.println("name = "+name+", age = " + age + ", sex is " + sex);
	}
	
	@Test(dataProvider = "data")
	public void TestDataProvider2(String name, int age, String sex)
	{
		System.out.println("name = "+name+", age = " + age + ", sex is " + sex);
	}
	
	@DataProvider(name = "data")
	public Object [][] dataPro(Method method)
	{
		Object[][] o = null;
		if(method.getName().equals("TestDataProvider"))
		{
			o = new Object[][]{
					{"zhangsan",1,"female"},
					{"zhaosi",2,"male"}
			};
		}
		else if (method.getName().equals("TestDataProvider2"))
		{
			o = new Object[][]
					{
					{"wangwu",3,"male"},
					{"sunliu",4,"female"}
					};
		}
		return o;
	}

}
