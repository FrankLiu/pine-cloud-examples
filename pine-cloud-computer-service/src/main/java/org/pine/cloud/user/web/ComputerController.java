package org.pine.cloud.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComputerController {
	private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);
	
	@Autowired
	private DiscoveryClient client;

	@GetMapping("/add")
	public Integer add(@RequestParam Integer a, @RequestParam Integer b){
		ServiceInstance instance = client.getInstances("compute-service").get(0);
		Integer r = a + b;
		logger.info("/add, host: {}, service_id: {}, result: {}", instance.getHost(), instance.getServiceId(), r);
		return r;
	}
}
