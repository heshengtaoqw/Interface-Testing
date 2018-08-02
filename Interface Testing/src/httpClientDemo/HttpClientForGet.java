package httpClientDemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HttpClientForGet {

	private String BaseURL;
	private String getCookieURI;
	private String sendCookieURI;
	private String GetCookieURL;
	private String SendCookieURL;
	private ResourceBundle bundle;
	private CookieStore store;
	private String result;
	
	@BeforeTest
	public void setTestURL () throws Exception
	{
		bundle = bundle.getBundle("resources/application", Locale.CHINA);
		BaseURL = bundle.getString("testurl");
		getCookieURI = bundle.getString("test.get.getcookie.uri");
		sendCookieURI = bundle.getString("test.get.sendcookie.uri");
		GetCookieURL = BaseURL+getCookieURI;
		SendCookieURL = BaseURL+sendCookieURI;
		
	}
	
	@Test
	public void getCookie() throws Exception
	{
		//设置Client
		DefaultHttpClient client = new DefaultHttpClient();
		//设置get请求
		HttpGet get = new HttpGet(GetCookieURL);
		//发送get请求
		HttpResponse response = client.execute(get);
		//判断结果是否正确
		int statusCode = response.getStatusLine().getStatusCode();
		//如果正确，获取响应的内容
		if(statusCode == 200)
		{
			//获取http请求内容
			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			
			//获取Cookie内容并打印
			this.store = client.getCookieStore();
			List<Cookie> cookieList = store.getCookies();
			for(Cookie cookie: cookieList)
			{
				String name = cookie.getName();
				String value = cookie.getValue();
				System.out.println(name + ": " + value);
			}
		}
	}
	
	@Test(dependsOnMethods="getCookie")
	public void sendCookie() throws Exception
	{
		//设置client
		DefaultHttpClient client = new DefaultHttpClient();
		//设置get请求
		HttpGet get = new HttpGet(SendCookieURL);
		//设置cookie
		client.setCookieStore(this.store);
		//发送带有cookie的get请求
		HttpResponse response = client.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();
		//如果正确，获取响应的内容
		if(statusCode == 200)
		{
			//获取http请求内容
			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			
			//获取Cookie内容并打印
			this.store = client.getCookieStore();
			List<Cookie> cookieList = store.getCookies();
			for(Cookie cookie: cookieList)
			{
				String name = cookie.getName();
				String value = cookie.getValue();
				System.out.println(name + ": " + value);
			}
		}
		
	}
	
	
}
