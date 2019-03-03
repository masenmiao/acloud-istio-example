package acloud.simple.service.impl.web;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserReactiveService;
import acloud.simple.service.spe.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class UserReactiveController {

	@Resource(name = "userRedisServiceImpl")
	UserReactiveService redisService;

	@GetMapping(value="/userRedis",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User> searchAllRedis(){
		return redisService.searchAll();
	}


	@GetMapping(value="/findByIdRedis")
	public Mono<User> findByIdRedis(@RequestParam("id") String id){
		System.out.println("message id is:"+id);
		return redisService.searchUser(id);
	}

}
