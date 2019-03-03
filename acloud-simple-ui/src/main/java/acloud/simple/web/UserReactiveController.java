/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserFeignService;
import feign.Feign;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserReactiveController {

    @Value("${simpleServiceName}")
    private String simpleServiceName = "cloud-simple-service";

    @GetMapping(value = "/fluxFindAll", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> fluxFindAll() {

        return WebClient.builder().baseUrl("http://" + simpleServiceName).build()
            .get().uri("/userRedis").accept(MediaType.APPLICATION_STREAM_JSON).exchange().flatMapMany(response -> {
                return response.bodyToFlux(User.class);
            });
    }

    @GetMapping(value = "/fluxFindById")
    public Mono<User> fluxFindById(@RequestParam("id") String id) {
        System.out.println("message id is:" + id);
        return WebClient.builder().baseUrl("http://" + simpleServiceName).build()
            .get().uri("/findByIdRedis?id=" + id)
            .retrieve()
            .bodyToMono(User.class);
    }

}
