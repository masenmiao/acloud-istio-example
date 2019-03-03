/*
 * Copyright 2012-2020 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * @author lzhoumail@126.com/zhouli
 * Git http://git.oschina.net/zhou666/spring-cloud-7simple
 */
package acloud.simple.service.impl;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRestCallService implements UserService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${simpleServiceName}")
    private String simpleServiceName = "cloud-simple-service";

    @Override
    public User findById(String id) {
        User user = restTemplate.getForObject("http://" + simpleServiceName + "/findById", User.class);
        return user;
    }

    @Override
    public List<User> searchAll() {
        List<User> list = null;
        try {
            list = restTemplate.getForObject("http://" + simpleServiceName + "/user", List.class);
        } catch (Exception ex) {//异常没有阻止断路器
            ex.printStackTrace();
        }
        return list;
    }

    private List<User> fallbackSearchAll() {
        System.out.println("HystrixCommand fallbackMethod handle!");
        List<User> ls = new ArrayList<User>();
        User user = new User();
        user.setName("TestHystrixCommand");
        ls.add(user);
        return ls;
    }
}
