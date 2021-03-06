package acloud.simple.service.impl.domain;


import acloud.simple.service.impl.dao.UserDao;
import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserReactiveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 业务实现类
 *
 * @author masen
 */
/**
ReactiveValueOperations 是 String（或 value）的操作视图，
操作视图还有 ReactiveHashOperations、ReactiveListOperations、ReactiveSetOperations 和 ReactiveZSetOperations 等
*/

@Slf4j
@Service
public class UserRedisServiceImpl implements UserReactiveService {


    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Autowired
    private UserDao userMapper;

    public static String USER = "USER";

    @Override
    public Flux<User> searchAll() {
        //		ReactiveValueOperations valueOperations = reactiveRedisTemplate.opsForValue();
//		valueOperations.set(key,value);

        ReactiveHashOperations<String, String, User> hashOperations = reactiveRedisTemplate.opsForHash();

        Flux<User> users = hashOperations.values(USER);

        return users;

    }

    /**
     * 初始化时放入
     * 增加修改时操作redis进行更新
     */
    @PostConstruct
    public void initCacheUsers() {
        System.out.println("初始化 redis users.");
        try {
            ReactiveHashOperations<String, String, User> hashOperations = reactiveRedisTemplate.opsForHash();
            List<User> list = userMapper.findAll();
            if (CollectionUtils.isNotEmpty(list)) {
                User usre = null;
                for (int i = 0; i < list.size(); i++) {
                    usre = list.get(i);
                    System.out.println(usre);
                    hashOperations.put(USER, usre.getId(), usre).block();
                }
            }
        }catch(Exception ex){
            log.warn("连接不上redis");
        }
    }

    @Override
    public Mono<User> searchUser(String id) {
        ReactiveHashOperations<String, String, User> operations = reactiveRedisTemplate.opsForHash();
        return operations.get(USER, id);

    }
}
