package com.users.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.entities.Result;
import com.users.entities.User;
import com.users.exception.DataValidationException;
import com.users.exception.UserNotFoundException;
import com.users.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Result saveUser(User user) {

		// Checking if name has been entered
		if (user.getUserFirstName().equals("")) {
			return new Result("ABE001", "First name mandatory.Please enter first name", "[]");
		}

		// Checking if email has been entered and also validating it
		if (user.getUserEmail().equals("")) {
			return new Result("ABE001", "Email mandatory.Please enter email");
		} else if (!user.getUserEmail()
				.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?")) {
			return new Result("ABE001", "Email should be in the format user@xxx.xxx ");
		} else {
			User uEmail = userRepository.findByUserEmail(user.getUserEmail());
			if (uEmail != null) {
				return new Result("ABE001", "Email already registered ", "[]");
			}
		}

		// Checking if email has been entered and also validating it
		if (user.getUserPhone().equals("")) {
			return new Result("ABE001", "Phone number mandatory.Please enter phone number", "[]");

		} else if (user.getUserPhone().length() < 14) {
			return new Result("ABE001", "Phone number should contain 14 digits including country code", "[]");

		} else {
			if (user.getUserPhone().length() == 14) {
				if (!user.getUserPhone().matches("[\\+]+[0-9]{2}+[-]+[0-9]{10}")) {
					return new Result("ABE001",
							"Phone number not valid.Should be in the format  +CountryCode-PhoneNumber  ", "[]");
				} else {
					User uPhone = userRepository.findByUserPhone(user.getUserPhone());
					if (uPhone != null) {
						return new Result("ABE001", "Phone number already regisrered ", "[]");
					}
				}

			}
		}

		// Validating password
		if (user.getUserPassword().length() < 8) {
			return new Result("ABE001", "Password must contain minimum eight characters ", "[]");

		} else {
			if (!user.getUserPassword().matches("^(?=.*?[A-Z])(?!.*\\W)(?=(.*\\d){2}).{8,}")) {
				return new Result("ABE001",
						"Password incorrect.Should contain only digits and characters and should contain atleast one uppercase character and 2 digits",
						"[]");
			}
		}
		return new Result("ABI001", "User added successfully", userRepository.save(user));
	}

	@Override
	public List<Result> saveUsersBatch(List<User> users) 
	{
		List<Result> batchResult=new ArrayList<>();
		int currentUser=1;
		for (User singleUser : users) 
		{
			// Checking if name has been entered
			if (singleUser.getUserFirstName().equals("")) {
				
				batchResult.add(new Result("ABE001", "User "+currentUser+": "+"First name mandatory.Please enter first name", "[]"));
				 return batchResult;
			}

			// Checking if email has been entered and also validating it
			if (singleUser.getUserEmail().equals("")) {
				batchResult.add(new Result("ABE001", "User "+currentUser+": "+"User "+currentUser+": "+"Email mandatory.Please enter email", "[]"));
				 return batchResult;
			} else if (!singleUser.getUserEmail()
					.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?")) {
				batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Email should be in the format user@xxx.xxx ", "[]"));
				 return batchResult;
			} else {
				User uEmail = userRepository.findByUserEmail(singleUser.getUserEmail());
				if (uEmail != null) {
					 batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Email already registered ", "[]"));
					 return batchResult;
				}
			}

			// Checking if email has been entered and also validating it
			if (singleUser.getUserPhone().equals("")) {
				batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Phone number mandatory.Please enter phone number", "[]"));
				 return batchResult;

			} else if (singleUser.getUserPhone().length() < 14) {
				batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Phone number should contain 14 digits including country code", "[]"));
				 return batchResult;

			} else {
				if (singleUser.getUserPhone().length() == 14) {
					if (!singleUser.getUserPhone().matches("[\\+]+[0-9]{2}+[-]+[0-9]{10}")) {
						batchResult.add(new Result("ABE001", "Phone number not valid.Should be in the format  +CountryCode-PhoneNumber  ", "[]"));
						 return batchResult;
					} else {
						User uPhone = userRepository.findByUserPhone(singleUser.getUserPhone());
						if (uPhone != null) {
							batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Phone number already regisrered ", "[]"));
							 return batchResult;
						}
					}
				}
			}

			// Validating password
			if (singleUser.getUserPassword().length() < 8) {
				batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Password must contain minimum eight characters ", "[]"));
				 return batchResult;

			} else {
				if (!singleUser.getUserPassword().matches("^(?=.*?[A-Z])(?!.*\\W)(?=(.*\\d){2}).{8,}")) {
					batchResult.add(new Result("ABE001", "User "+currentUser+": "+"Password incorrect.Should contain only digits and characters and should contain atleast one uppercase character and 2 digits", "[]"));
					 return batchResult;
				}
			} 
			 batchResult.add(new Result("ABI001", "User "+currentUser+" added successfully", userRepository.save(singleUser)));
		}
		return  batchResult;
	}

	@Override
	public Result getAllUsers() {
		return new Result("ABI001", "fetched users successfully", userRepository.findAll());
	}

	@Override
	public Result getUserById(String userId) {

		Optional<User> userDummy=userRepository.findById(userId);
		if ( userDummy.isEmpty()) {
			return new Result("ABI001", "User not found", "[]");
		}

		return new Result("ABI001", "User fetched successfully", userRepository.findById(userId));
	}

	@Override
	public Result getUserByEmailOrPhone(String userData) {
		
		if ( userRepository.findByUserEmail(userData)== null) {
			if (userRepository.findByUserPhone(userData) == null) {
				return new Result("ABI001", "User not found", "[]");
			} else {
				return new Result("ABI001", "User fetched successfully using phone number",
						userRepository.findByUserPhone(userData));
			}
		} else {
			return new Result("ABI001", "User fetched successfully using email",
					userRepository.findByUserEmail(userData));
		}
	}

	@Override
	public Result deleteUserById(String userId) {
		if (userRepository.findById(userId) == null) {
			return new Result("ABI001", "User not found", "[]");
		}
		userRepository.deleteById(userId);
		return new Result("ABI001", "User deleted successfully ", "[]");
	}

	@Override
	public Result updateUserById(String userId, User user) {

		if(userRepository.findById(userId)==null)
		{
			return new Result("ABI001", "User not found", "[]");
		}
		User userDb=userRepository.findById(userId).get();
			if (Objects.nonNull(user.getUserFirstName())) {
				if (user.getUserFirstName().equals("")) {
					return new Result("ABE001", "First name mandatory.Please enter first name", "[]");
				}
				userDb.setUserFirstName(user.getUserFirstName());
			}
			if (Objects.nonNull(user.getUserLastName())) {
				userDb.setUserLastName(user.getUserLastName());
			}
			if (Objects.nonNull(user.getUserEmail())) {
				if (user.getUserEmail().equals("")) {
					return new Result("ABE001", "Email mandatory.Please enter email");
				}
				else
				{
					 if (!user.getUserEmail()
								.matches("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?"))
					 {
							return new Result("ABE001", "Email should be in the format user@xxx.xxx ");
					 }
				}
				userDb.setUserEmail(user.getUserEmail());
			}
			if (Objects.nonNull(user.getUserDob())) {
				userDb.setUserDob(user.getUserDob());
			}
			if (Objects.nonNull(user.getUserGender())) {
				userDb.setUserGender(user.getUserGender());
			}
			if (Objects.nonNull(user.getUserPassword())) {
				if (user.getUserPassword().length() < 8) {
					return new Result("ABE001", "Password must contain minimum eight characters ", "[]");

				} else {
					if (!user.getUserPassword().matches("^(?=.*?[A-Z])(?!.*\\W)(?=(.*\\d){2}).{8,}")) {
						return new Result("ABE001",
								"Password incorrect.Should contain only digits and characters and should contain atleast one uppercase character and 2 digits",
								"[]");
					}
				}
				userDb.setUserPassword(user.getUserPassword());
			}
			if (Objects.nonNull(user.getUserPhone())) {
				if (user.getUserPhone().equals("")) {
					return new Result("ABE001", "Phone number mandatory.Please enter phone number", "[]");
				} else if (user.getUserPhone().length() < 14) {
					return new Result("ABE001", "Phone number should contain 14 digits including country code", "[]");
				} else {
					if (user.getUserPhone().length() == 14) {
						if (!user.getUserPhone().matches("[\\+]+[0-9]{2}+[-]+[0-9]{10}")) {
							return new Result("ABE001", "Phone number not valid.Should be in the format  +CountryCode-PhoneNumber  ", "[]");
						}
					}
				}
				userDb.setUserPhone(user.getUserPhone());
			}
			if (Objects.nonNull(user.getUserPhoto())) {
				userDb.setUserPhoto(user.getUserPhoto());
			}
			if (Objects.nonNull(user.getUserCreatedDate())) {
				userDb.setUserCreatedDate(user.getUserCreatedDate());
			}
			if (Objects.nonNull(user.getUserUpdatedDate())) {
				userDb.setUserUpdatedDate(user.getUserUpdatedDate());
			}

			
			return new Result("ABE001", "User updated successfully", userRepository.save(userDb)); 

	}

	@Override
	public Result getUsersAfterDate(String startDate, String endDate)  {
		List<User> userListDb = userRepository.findAll();
		if (LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))) {
			return new Result("ABE001", "Start date should not be after end date","[]");
		}
		List<User> filteredUser = userListDb.stream().filter(userDate -> {
			if ((LocalDate.parse(userDate.getUserCreatedDate()).isEqual(LocalDate.parse(startDate))
					|| LocalDate.parse(userDate.getUserCreatedDate()).isAfter(LocalDate.parse(startDate)))
					&& (LocalDate.parse(userDate.getUserCreatedDate()).isEqual(LocalDate.parse(endDate))
							|| LocalDate.parse(userDate.getUserCreatedDate()).isBefore(LocalDate.parse(endDate)))) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
		return new Result("ABE001","Users created between the given dates are", filteredUser);
	}

}
