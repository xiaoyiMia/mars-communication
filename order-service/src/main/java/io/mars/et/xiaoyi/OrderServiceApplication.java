package io.mars.et.xiaoyi;

import io.mars.et.xiaoyi.common.IdGenerator;
import io.mars.et.xiaoyi.common.UUIDGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public IdGenerator idGenerator() {
		return new UUIDGenerator();
	}

}
