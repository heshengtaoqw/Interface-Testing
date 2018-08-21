package httpClientDemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class HttpClientForPost {

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
		getCookieURI = bundle.getString("test.post.getcookie.uri");
		sendCookieURI = bundle.getString("test.post.sendcookie.uri");
		GetCookieURL = BaseURL+getCookieURI;
		SendCookieURL = BaseURL+sendCookieURI;
		
	}
	
	@Test
	public void getCookie() throws Exception
	{
		//设置Client
		DefaultHttpClient client = new DefaultHttpClient();
		//设置post请求
		HttpPost post = new HttpPost(GetCookieURL);
		//设置参数
		JSONObject param = new JSONObject();
		param.put("name", "zhangsan");
		param.put("age", "45");
		StringEntity entity = new StringEntity(param.toString());
		post.setEntity(entity);
		//发送post请求
		HttpResponse response = client.execute(post);
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
		HttpPost post = new HttpPost(SendCookieURL);
		//设置参数
		JSONObject param = new JSONObject();
		param.put("name", "wangliu");
		param.put("age", "40");
		StringEntity entity = new StringEntity(param.toString());
		post.setEntity(entity);
		//设置头字段
		post.setHeader("Content-Type", "application/json");
		//设置cookie
		client.setCookieStore(this.store);
		//发送带有cookie的get请求
		HttpResponse response = client.execute(post);
		
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
