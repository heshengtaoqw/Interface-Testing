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
		//��ȡ�����ļ���·���������src����д�����ļ������ƣ�������ڣ���Ҫ�Ӱ�/�ļ�������Locale.CHINAΪ�ַ���
		bundle = ResourceBundle.getBundle("resources/application", Locale.CHINA);
		url = bundle.getString("testurl");
		uri = bundle.getString("testuri");
	}
	
	@Test
	public void getCookie() throws Exception
	{
		//ƴ�Ӳ��Ե�uri
		String test = this.url+this.uri;
		//��������ĵ�ַ
		HttpGet get = new HttpGet(test);
		//����httpClient�ͻ���
		DefaultHttpClient client = new DefaultHttpClient();
		//ִ������
		HttpResponse response = client.execute(get);
		//��ȡ��Ӧ����
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
		//����cookie��Ϣ
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
