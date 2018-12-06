package com.draysams.eventsourcedusermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class EventsourcedUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsourcedUserManagementApplication.class, args);
	}
}
