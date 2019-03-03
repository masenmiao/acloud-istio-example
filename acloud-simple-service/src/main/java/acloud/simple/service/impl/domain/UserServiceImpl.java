package acloud.simple.service.impl.domain;


import acloud.simple.service.impl.dao.UserDao;
import acloud.simple.service.spe.UserService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import acloud.simple.service.data.User;

/**
 * 业务实现类
 * @author masen
 *
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserDao userMapper;
	@Override
	public List<User> searchAll(){
		List<User> list = userMapper.findAll();
		return list;
	}
	@Override
	public User findById(String id){
		return userMapper.findById(id);

	}
}
