package com.vt.DAO;

import java.util.List;

import com.vt.model.User;

public interface UserDAO {
	void addUser(User user);
	User getUser(int userId);
	void updateUser(User user);
	void deleteUser(int userId);
	List<User> getAllUsers();
	User validateUser(String email,String password);
}
