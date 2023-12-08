package com.semester.dto;

import java.time.Month;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseRequest {

	@Size(min = 1, message = "Course name should be at least 1 character")
	private String name;
	
	@DecimalMin("0.0")
	private float duration;
	
	@DecimalMin("0.0")
	private float cost;
}
