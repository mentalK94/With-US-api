package kr.co.mentalK94.withus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@EnableAsync
@SpringBootApplication
public class WithUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithUsApplication.class, args);
	}

}
