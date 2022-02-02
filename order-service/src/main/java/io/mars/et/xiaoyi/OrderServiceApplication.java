package io.mars.et.xiaoyi;

import io.mars.et.xiaoyi.common.IdGenerator;
import io.mars.et.xiaoyi.common.UUIDGenerator;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public IdGenerator idGenerator() {
		return new UUIDGenerator();
	}

	@Bean
	public DeadlineManager deadlineManager(AxonConfiguration config, PlatformTransactionManager transactionManager) {
		return SimpleDeadlineManager.builder()
				.scopeAwareProvider(new ConfigurationScopeAwareProvider(config))
				.transactionManager(new SpringTransactionManager(transactionManager))
				.build();
	}

}
