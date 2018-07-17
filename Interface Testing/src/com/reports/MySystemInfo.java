package com.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.vimalselvam.testng.SystemInfo;
import com.vimalselvam.testng.listener.*;

import org.testng.collections.Maps;

public class MySystemInfo implements SystemInfo{
	
	public Map<String,String> getSystemInfo()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,String> m = Maps.newHashMap();
		m.put("username", "tom");
		m.put("running time", df.format(new Date()));
		return m;
	}
}
