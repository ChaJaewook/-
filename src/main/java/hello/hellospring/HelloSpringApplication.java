package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
	//내장 톰캣

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
