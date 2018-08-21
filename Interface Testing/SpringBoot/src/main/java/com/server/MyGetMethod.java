package com.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //告诉入口我需要被扫描
public class MyGetMethod {

	@RequestMapping(value = "/getCookies", method = RequestMethod.GET) //定义访问的地址
	public String getCookies(HttpServletResponse response)
	{
		//HttpServletRequest   定义该方法需要的request
		//HttpServletResponse   定义该方法返回的response
		Cookie cookie = new Cookie("login","true");
		response.addCookie(cookie);
		return "恭喜你获得cookies";
	}
	
	@RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
	public String getWithCookies(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if(Objects.isNull(cookies))
		{
			return "你需要带有Cookie信息访问";
		}
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("login")&&cookie.getValue().equals("true"))
			{
				return "恭喜你带着cookie访问成功";
			}
		}
		return "恭喜你访问成功";
		
	}
	
	/*
	 * 带有参数传递的get请求，第一种实现方式
	 * url =  ip:port/path?param1&param2
	 */
	
	@RequestMapping(value = "/get/with/param",method = RequestMethod.GET)
	public Map<String,Integer> getList(@RequestParam Integer start, @RequestParam Integer end)
	{
		Map<String,Integer> mylist = new HashMap<>();
		
		mylist.put("衣服", 200);
		mylist.put("苹果", 3);
		mylist.put("巧克力", 10);

		return mylist;
	}
	
	
	/*
	 * 带有参数传递的get请求，第二种实现方式
	 * url =  ip/port/path/param1/param2
	 */
	
	@RequestMapping(value = "/get/with/path/{start}/{end}",method = RequestMethod.GET)
	public Map getList2(@PathVariable Integer start, @PathVariable Integer end)
	{
		Map<String,Integer> mylist = new HashMap<>();
		
		mylist.put("衣服", 200);
		mylist.put("苹果", 3);
		mylist.put("巧克力", 10);

		return mylist;
	}
	
	
}
