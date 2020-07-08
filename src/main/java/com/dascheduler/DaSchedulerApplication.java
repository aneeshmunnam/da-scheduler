package com.dascheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = "com.dascheduler.model")
@EnableJpaRepositories(basePackages="com.dascheduler.repository")
@EnableTransactionManagement
public class DaSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaSchedulerApplication.class, args);
	}
}
