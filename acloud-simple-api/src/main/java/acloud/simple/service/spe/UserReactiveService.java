package acloud.simple.service.spe;

import acloud.simple.service.data.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

// openfeign 不支持 reactive，需要增加其它依赖支持
public interface UserReactiveService {
    @RequestLine("GET /fluxSearchAll")
    public Flux<User> searchAll();

    @RequestLine("GET /fluxSearchUser?id={id}")
    public Mono<User> searchUser(
        @Param(value = "id") String id);
}
