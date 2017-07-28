package za.co.ajk.springretry.retryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import za.co.ajk.springretry.retryclient.listeners.DefaultListenerSupport;


@EnableHystrixDashboard

/**
 * To enable the Hystrix based trigger and remove SptringRetry, enable the following annotation
 * @EnableCircuitBreaker
 * and remove 
 * @EnableRetry
 */
@EnableRetry
@SpringBootApplication
public class RetryclientApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RetryclientApplication.class, args);
		
	}
	
	
	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		
		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
		fixedBackOffPolicy.setBackOffPeriod(2000l);
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		
		SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
		retryPolicy.setMaxAttempts(2);
		retryTemplate.setRetryPolicy(retryPolicy);
		
		retryTemplate.registerListener(new DefaultListenerSupport());
		return retryTemplate;
	}
}
