package httpClientDemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.sf.json.JSONObject;

public class HttpClientPostCookie {
	
	private String url;
	private String getCookieURL;
	private String sendCookieURL;
	private ResourceBundle bundle;
	private CookieStore store;
	
	//通过配置文件导入参数
	@BeforeTest
	public void beforeTest()
	{
		bundle = bundle.getBundle("resources/application",Locale.CHINA);
		url = bundle.getString("testurl");
		getCookieURL = bundle.getString("test.post.getcookie.uri");
		sendCookieURL = bundle.getString("test.post.sendcookie.uri");
	}
	
	@Test
	public void getPostCookie() throws Exception
	{
		//拼接参数
		String testURL = url + getCookieURL;
		//创建一个Client
		DefaultHttpClient client = new DefaultHttpClient();
		//创建一个Post请求
		HttpPost post = new HttpPost(testURL);
		//设置参数
		JSONObject param = new JSONObject();
		param.put("name", "zhangsan");
		param.put("age", "45");
		//设置请求头信息（header）
		//将参数信息设置到post中,把param 的json对象转成String
		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		post.setEntity(entity);
		//执行post请求
		HttpResponse response = client.execute(post);
		//获取cookie
		store = client.getCookieStore();
		List<Cookie> cookieList = store.getCookies();
		for (Cookie cookie:cookieList)
		{
			String name = cookie.getName();
			String value = cookie.getValue();
			System.out.println("cookie name is " + name + "; cookie value is " + value );
		}
		System.out.println(response.getStatusLine().getStatusCode());
	}
	
	@Test(dependsOnMethods = {"getPostCookie"})
	public void sendPostCookie() throws Exception
	{
		//拼接参数
		String testURL = url + sendCookieURL;
		//创建一个Client
		DefaultHttpClient client = new DefaultHttpClient();
		//创建一个Post请求
		HttpPost post = new HttpPost(testURL);
		//设置参数
		JSONObject param = new JSONObject();
		param.put("name", "wangliu");
		param.put("age", "40");
		//设置请求头信息（header）
		post.setHeader("Content-Type","application/json");
		//将参数信息设置到post中,把param 的json对象转成String
		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		post.setEntity(entity);
		//设置Cookie到浏览器中
		client.setCookieStore(this.store);
		//执行post请求
		HttpResponse response = client.execute(post);
		//获取响应信息
		String result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		
	}
}
