package org.pine.cloud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputerController {
	private static final Logger logger = LoggerFactory.getLogger(ComputerController.class);

	@GetMapping("/add")
	public Integer add(@RequestParam Integer a, @RequestParam Integer b){
		Integer r = a + b;
		logger.info("/add, params: {}, result: {}", a, b, r);
		return r;
	}
}
