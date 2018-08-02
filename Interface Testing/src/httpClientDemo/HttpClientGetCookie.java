package httpClientDemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HttpClientGetCookie {
	
	private String url;
	private String uri;
	private ResourceBundle bundle;
	private String result;
	private CookieStore store;
	
	@BeforeTest
	public void getURL()
	{
		//获取配置文件的路径，如果在src下则写配置文件的名称，如果不在，则要加包/文件夹名；Locale.CHINA为字符集
		bundle = ResourceBundle.getBundle("resources/application", Locale.CHINA);
		url = bundle.getString("testurl");
		uri = bundle.getString("testuri");
	}
	
	@Test
	public void getCookie() throws Exception
	{
		//拼接测试的uri
		String test = this.url+this.uri;
		//发送请求的地址
		HttpGet get = new HttpGet(test);
		//启动httpClient客户端
		DefaultHttpClient client = new DefaultHttpClient();
		//执行请求
		HttpResponse response = client.execute(get);
		//获取响应内容
		result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		
		
		 store = client.getCookieStore();
		List<Cookie> cookieList = store.getCookies();
		
		for(Cookie cookie : cookieList)
		{
			String value = cookie.getValue();
			String name = cookie.getName();
			System.out.println("cookie value = " + value + " cookie name = " + name );
		}
	}
	
	@Test(dependsOnMethods = {"getCookie"})
	public void sendCookie() throws Exception
	{
		String sendCookieURL = this.url + bundle.getString("test.send.cookie.uri");
		HttpGet get = new HttpGet(sendCookieURL);
		DefaultHttpClient client = new DefaultHttpClient();
		//设置cookie信息
		client.setCookieStore(this.store);
		HttpResponse response = client.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		
		if(statusCode == 200)
		{
			store = client.getCookieStore();
			List<Cookie> cookieList = store.getCookies();
			for(Cookie cookie : cookieList)
			{
				System.out.println(EntityUtils.toString(response.getEntity()));
			}
		}
		
	}

}
