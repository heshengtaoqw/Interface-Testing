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
		//����ĵ�ַ
		HttpGet get = new HttpGet("http://www.baidu.com");
		//�����������
		HttpClient client = new DefaultHttpClient();
		//ִ��get��������ȡ��Ӧ������
		HttpResponse response = client.execute(get);
		//����Ӧ����Ϊstring��ʽ
		result = EntityUtils.toString(response.getEntity());
		System.out.println(result);
	}

}
