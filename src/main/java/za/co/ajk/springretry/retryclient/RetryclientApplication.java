package za.co.ajk.springretry.retryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.retry.annotation.EnableRetry;


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
}
