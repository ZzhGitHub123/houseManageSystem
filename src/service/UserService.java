package service;

import java.util.List;

import Pojo.User;
import Pojo.Userlist;

public interface UserService {

	public List<User> userList() throws Exception;
	public List<User> listUserByName(User user) throws Exception;
	public User login(User user) throws Exception;
	public int register(User user) throws Exception;
	
}
