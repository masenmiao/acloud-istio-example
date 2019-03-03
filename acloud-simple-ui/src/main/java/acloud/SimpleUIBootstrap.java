/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication

public class SimpleUIBootstrap {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SimpleUIBootstrap.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
