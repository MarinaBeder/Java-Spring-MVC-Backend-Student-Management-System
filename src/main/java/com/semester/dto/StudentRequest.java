package com.semester.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequest {
	@Size(min = 2, message = "first name should not be less than 2 characters")
	@Size(max = 20, message = "first name should not be more than 20 characters")
	private String firstName;
	
	@Size(min = 2, message = "last name should not be less than 2 characters")
	@Size(max = 20, message = "last name should not be more than 20 characters")
	private String lastName;
	
	@Email
	private String email;
	
	@Pattern(regexp = "(^$|[0-9]{11})", message = "Please, write correct phone")
	private String phoneNumber;
	
	@Size(min = 5, message = "address should not be less than 5 characters")
	@Size(max = 100, message = "address should not be more than 100 characters")
	private String address;
}
