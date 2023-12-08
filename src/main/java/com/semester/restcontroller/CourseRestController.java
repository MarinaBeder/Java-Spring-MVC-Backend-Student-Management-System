package com.semester.restcontroller;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semester.domain.Course;
import com.semester.domain.Message;
import com.semester.dto.CourseRequest;
import com.semester.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Course RestController", description = "using @RestController")

@RestController
@RequestMapping("rest/v1/course")
public class CourseRestController {

	@Autowired
	CourseService courseService;

	@Operation(description = "", summary = "To create course", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/")
	public ResponseEntity<?> saveCourse(@Valid @RequestBody CourseRequest courseRequest) {
		return ResponseEntity.ok(courseService.saveCourse(courseRequest));
	}

	@Operation(description = "", summary = "To get specific course", responses = {
			@ApiResponse(description = "OK", responseCode = "200")}

	)
	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourseById(@PathVariable long courseId) {
		return ResponseEntity.ok(courseService.getCourseById(courseId));
	}

	@Operation(description = "", summary = "To delete specific course", responses = {
			@ApiResponse(description = "OK", responseCode = "200")}

	)
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Message> deleteCourseById(@PathVariable Long courseId) {
		return ResponseEntity.ok(courseService.deleteCourseById(courseId));
	}

	@Operation(description = "", summary = "To update specific course", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@PutMapping("/{courseId}")
	public ResponseEntity<Course> updateCourseById(@PathVariable long courseId,
			@Valid @RequestBody CourseRequest courseRequest) {
		return ResponseEntity.ok(courseService.updateCourseById(courseRequest, courseId));
	}

	@Operation(description = "user will put part of course name and we will search about "
			+ "all courses that matching this word", summary = "To search about course using part of name", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("search/{searching-word}")
	public ResponseEntity<List<Course>> GetAllWordMatchingCourses(@RequestParam String searchingWord) {
		return ResponseEntity.ok(courseService.GetAllWordMatchingCourses(searchingWord));
	}
}
