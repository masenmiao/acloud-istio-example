package acloud.simple.service.spe;

import acloud.simple.service.data.User;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * feign 接口定义 ， 接口由service-simple实现，web-simple调用
 * @author think
 *
 */

//service id : cloud-simple-service
public interface UserFeignService {

	@RequestLine("GET /searchAll")
	public List<User> searchAll();

	@RequestLine("GET /feignUser?id={id}")
	public User searchUser(
		@Param(value = "id") String id);
}
