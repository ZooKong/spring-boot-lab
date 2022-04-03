package org.zookong.springboot.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"org.zookong.springboot.lab.config.application",
				"org.zookong.springboot.lab.core.*",
				"org.zookong.springboot.lab.restapi.*",
		}
)
public class SpringbootLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLabApplication.class, args);
	}

}