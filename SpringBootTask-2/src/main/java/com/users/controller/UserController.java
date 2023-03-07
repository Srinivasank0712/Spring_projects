package com.users.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.users.entities.Result;
import com.users.entities.User;
import com.users.exception.DataValidationException;
import com.users.exception.UserNotFoundException;
import com.users.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/users/")
	public Result saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/users/save/batch/")
	public List<Result> saveUser(@RequestBody List<User> users) {
		return userService.saveUsersBatch(users);
	}

	@GetMapping("/users/")
	public Result getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public Result getUserById(@PathVariable("id") String userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/users/phone/email/{data}")
	public Result getUserByEmailOrPhone(@PathVariable("data") String userData) {
		return userService.getUserByEmailOrPhone(userData);
	}

	@DeleteMapping("/users/{id}")
	public Result deleteUserById(@PathVariable("id") String userId)  {
		return userService.deleteUserById(userId);
	}

	@PutMapping("/users/{id}")
	public Result updateUserById(@PathVariable("id") String userId, @RequestBody User user)
			throws UserNotFoundException, DataValidationException {
		return userService.updateUserById(userId, user);
	}
	
	@GetMapping("/usersAfter/{sdate}/{edate}")
	public Result getUsersAfterSpecifiedDate(@PathVariable("sdate") String startDate,@PathVariable("edate") String endDate) 
	{
		return userService.getUsersAfterDate(startDate,endDate);
	}
}
