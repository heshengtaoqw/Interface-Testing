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
		//����Client
		DefaultHttpClient client = new DefaultHttpClient();
		//����get����
		HttpGet get = new HttpGet(GetCookieURL);
		//����get����
		HttpResponse response = client.execute(get);
		//�жϽ���Ƿ���ȷ
		int statusCode = response.getStatusLine().getStatusCode();
		//�����ȷ����ȡ��Ӧ������
		if(statusCode == 200)
		{
			//��ȡhttp��������
			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			
			//��ȡCookie���ݲ���ӡ
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
		//����client
		DefaultHttpClient client = new DefaultHttpClient();
		//����get����
		HttpGet get = new HttpGet(SendCookieURL);
		//����cookie
		client.setCookieStore(this.store);
		//���ʹ���cookie��get����
		HttpResponse response = client.execute(get);
		
		int statusCode = response.getStatusLine().getStatusCode();
		//�����ȷ����ȡ��Ӧ������
		if(statusCode == 200)
		{
			//��ȡhttp��������
			result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			
			//��ȡCookie���ݲ���ӡ
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
