/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */

package acloud.simple.web;

import acloud.simple.service.data.User;

import acloud.simple.service.spe.UserService;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${simpleServiceName}")
    private String simpleServiceName = "acloud-simple-service";

    /**
     * feign RPC方式调用方式，最佳实践
     */
    @RequestMapping(value = "/findUser")
    public User queryUserByID(@RequestParam("id") String id) {
        User user = restTemplate.getForObject("http://" +
            simpleServiceName + "/findById?id={?}", User.class, id);
        return user;
    }

    @RequestMapping(value = "/users")
    public List<User> queryUserAll() {

        List<User> list = null;
        try {
            list = restTemplate.getForObject("http://" + simpleServiceName + "/user", List.class);
        } catch (Exception ex) {//异常没有阻止断路器
            ex.printStackTrace();
        }
        return list;
    }

}
