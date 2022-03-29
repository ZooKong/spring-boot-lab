package org.jjr.flowerbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"org.jjr.flowerbook.config.application",
				"org.jjr.flowerbook.core.*",
				"org.jjr.flowerbook.restapi.*",
		}
)
public class FlowerbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerbookApplication.class, args);
	}

}