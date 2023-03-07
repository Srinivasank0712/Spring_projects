package com.users.service;

import java.util.List;
import java.util.Map;

import com.users.entities.Result;
import com.users.entities.User;
import com.users.exception.DataValidationException;
import com.users.exception.UserNotFoundException;

public interface UserService {

	public Result saveUser(User user);
	public List<Result> saveUsersBatch(List<User> users);
	public Result getAllUsers();
	public Result getUserById(String userId);
	public Result getUserByEmailOrPhone(String userData);
	public Result deleteUserById(String userId);
	public Result updateUserById(String userId,User user);
	public Result getUsersAfterDate(String startDate,String endDate);
}
