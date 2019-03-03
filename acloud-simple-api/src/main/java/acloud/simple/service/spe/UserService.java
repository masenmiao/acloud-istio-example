package acloud.simple.service.spe;


import acloud.simple.service.data.User;
import java.util.List;

/**
 * Service ID : acloud-simple-service
 */
public interface UserService {

    /**
     * rest url : findById
     */
	public List<User> searchAll();

    /**
     * rest url : user
     */
	public User findById(String id);
}
