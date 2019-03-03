package acloud.simple.service.impl.web;

import acloud.simple.service.data.User;
import acloud.simple.service.spe.UserReactiveService;
import feign.Param;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserReactiveFeignController implements UserReactiveService{

	@Resource(name = "userRedisServiceImpl")
	UserReactiveService redisService;

	@GetMapping(value="/fluxSearchAll",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<User> searchAll(){
		return redisService.searchAll();
	}


	@GetMapping(value="/fluxSearchUser")
	public Mono<User> searchUser(
		@Param(value = "id") String id){
		System.out.println("message id is:"+id);
		return redisService.searchUser(id);
	}

}
