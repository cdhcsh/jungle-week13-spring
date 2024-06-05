package org.jungle.code_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CodePostApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodePostApplication.class, args);
	}

}
