package httpClientDemo;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class HttpClientTest {
	@Test
	public void test() throws Exception
	{
		String result;
		//请求的地址
		HttpGet get = new HttpGet("http://www.baidu.com");
		//启动的浏览器
		HttpClient client = new DefaultHttpClient();
		//执行get方法，获取相应的内容
		HttpResponse response = client.execute(get);
		//将相应保存为string格式
		result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
	}

}
