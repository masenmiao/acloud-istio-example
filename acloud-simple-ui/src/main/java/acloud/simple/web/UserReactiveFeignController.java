/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserFeignService;
import acloud.simple.service.spe.UserReactiveService;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.reactive.ReactorFeign;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/feign")
public class UserReactiveFeignController implements UserReactiveService{

    @Value("${simpleServiceName}")
    private String simpleServiceName = "cloud-simple-service";

    UserReactiveService userReactiveService;

    @PostConstruct
    private void init() {
        userReactiveService = ReactorFeign
            .builder().decoder(new JacksonDecoder()).target(UserReactiveService.class, "http://" + simpleServiceName);
    }

    //问题：至返回了第一个元素，应该是接口声明少了类似MeidaType这样的属性
    @RequestMapping(value = "/fluxFindAll",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Override public Flux<User> searchAll() {

        return userReactiveService.searchAll();
    }

    @RequestMapping(value = "/fluxFindById")
    @Override public Mono<User> searchUser(@RequestParam("id") String id) {
        return userReactiveService.searchUser(id);
    }
}
