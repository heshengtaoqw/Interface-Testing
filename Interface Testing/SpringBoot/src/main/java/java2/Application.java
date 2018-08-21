package java2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.server")  //告诉入口要做扫描，要在哪些包下做扫描
public class Application {

	public static void main(String args[])
	{
		SpringApplication.run(Application.class,args);
	}
}
