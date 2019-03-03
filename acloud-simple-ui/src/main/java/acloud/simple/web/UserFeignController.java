/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserFeignService;
import acloud.simple.service.spe.UserService;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feign")
public class UserFeignController {

    @Value("${simpleServiceName}")
    private String simpleServiceName = "cloud-simple-service";

    UserFeignService userFeignService;

    @PostConstruct
    private void init() {
        userFeignService = Feign
            .builder().decoder(new JacksonDecoder()).target(UserFeignService.class, "http://" + simpleServiceName);
    }

    /**
     * feign 脱离SpringCloud的 RPC方式调用方式
     */
    @RequestMapping(value = "/findUser")
    public User queryUserByID(@RequestParam("id") String id) {
        System.out.println("id : " + id);
        User user = userFeignService.searchUser(id);
        System.out.println("user :" + user);
        return user;
    }

    @RequestMapping(value = "/users")
    public List<User> queryUserAll() {

        List<User> users = userFeignService.searchAll();
        return users;
    }

}
