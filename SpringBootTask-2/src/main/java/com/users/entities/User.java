package com.users.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_Id")
	private String userId;
	@Column(name = "first_name")
	private String userFirstName;
	@Column(name = "last_name")
	private String userLastName;
	@Column(name = "email")
	private String userEmail;
	@Column(name = "password")
	private String userPassword;
	@Column(name = "gender")
	private String userGender;
	@Column(name = "user_DOB")
	private String userDob;
	@Column(name = "profile_photo")
	private String userPhoto;
	@Column(name = "created_date")
	private String userCreatedDate;
	@Column(name = "updated_date")
	private String userUpdatedDate;
	@Column(name = "phone_number")
	private String userPhone;
}
