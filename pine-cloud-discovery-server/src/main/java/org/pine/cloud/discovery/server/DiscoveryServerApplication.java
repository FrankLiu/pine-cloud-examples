package org.pine.cloud.discovery.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(DiscoveryServerApplication.class).web(true).run(args);
	}
}
