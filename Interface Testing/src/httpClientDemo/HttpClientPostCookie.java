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
	
	//ͨ�������ļ��������
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
		//ƴ�Ӳ���
		String testURL = url + getCookieURL;
		//����һ��Client
		DefaultHttpClient client = new DefaultHttpClient();
		//����һ��Post����
		HttpPost post = new HttpPost(testURL);
		//���ò���
		JSONObject param = new JSONObject();
		param.put("name", "zhangsan");
		param.put("age", "45");
		//��������ͷ��Ϣ��header��
		//��������Ϣ���õ�post��,��param ��json����ת��String
		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		post.setEntity(entity);
		//ִ��post����
		HttpResponse response = client.execute(post);
		//��ȡcookie
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
		//ƴ�Ӳ���
		String testURL = url + sendCookieURL;
		//����һ��Client
		DefaultHttpClient client = new DefaultHttpClient();
		//����һ��Post����
		HttpPost post = new HttpPost(testURL);
		//���ò���
		JSONObject param = new JSONObject();
		param.put("name", "wangliu");
		param.put("age", "40");
		//��������ͷ��Ϣ��header��
		post.setHeader("Content-Type","application/json");
		//��������Ϣ���õ�post��,��param ��json����ת��String
		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		post.setEntity(entity);
		//����Cookie���������
		client.setCookieStore(this.store);
		//ִ��post����
		HttpResponse response = client.execute(post);
		//��ȡ��Ӧ��Ϣ
		String result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
		
	}
}
