package acloud.simple.service.impl.web;

import acloud.simple.service.data.User;
import acloud.simple.service.impl.dao.UserDao;
import acloud.simple.service.spe.UserFeignService;
import acloud.simple.service.spe.UserService;
import feign.Param;
import feign.RequestLine;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * feign rpc实现
 * 其它模块通过 依赖接口 feign 调用
 * @author masen
 *
 */
@RestController
public class UserFeignController implements UserFeignService {

	@Resource(name = "userServiceImpl")
	UserService userService;

	@Override
	@RequestMapping(value="/searchAll")
	public List<User> searchAll(){
		List<User> list = userService.searchAll();
		return list;
	}
	@Override
	@RequestMapping(value="/feignUser")
	public User searchUser(
		@Param(value = "id") String id) {
		System.out.println("id : " + id);
		User user = userService.findById(id);
		return user;
	}
}
