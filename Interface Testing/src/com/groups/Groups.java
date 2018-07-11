package com.groups;
import org.testng.annotations.*;

public class Groups {
	
	@Test(groups = "server")
	public void TestGroup1()
	{
		System.out.println("Group1");
	}

	@Test(groups = "server2")
	public void TestGroup2()
	{
		System.out.println("Group2");
	}
	
	@BeforeGroups("server")
	public void TestBeforeGroup()
	{
		System.out.println("Before Groups");
	}
	
	@AfterGroups("server")
	public void TestAfterGroup()
	{
		System.out.println("Before Groups");
	}
}
