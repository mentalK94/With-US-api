package kr.co.mentalK94.withus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class WithUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithUsApplication.class, args);
	}

}
