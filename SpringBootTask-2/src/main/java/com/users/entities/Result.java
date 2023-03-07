package com.users.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

	private String code;
	private String message;
	private Object data;
	//private List<User> userList;
	public Result(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
//	public Result(String code, String message, List<User> userList) {
//		super();
//		this.code = code;
//		this.message = message;
//		this.userList = userList;
//	}
	
	
	
	
}
