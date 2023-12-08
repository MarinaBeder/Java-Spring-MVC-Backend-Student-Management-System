package com.semester.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semester.domain.Message;
import com.semester.domain.Student;
import com.semester.dto.CourseRequest;
import com.semester.dto.studentsIdRequest;
import com.semester.service.CourseStudentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Course-Students RestController", description = "using @RestController")

@RestController
@RequestMapping("rest/v1/course")
public class CourseStudentsRestController {
	@Autowired
	CourseStudentsService courseStudentsService;

	@Operation(description = "", summary = "add List of students to course using course Id ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/{courseId}/students")
	public ResponseEntity<Message> addStudentsToCourse(@Valid @RequestBody List<studentsIdRequest> StudentsId,
			@PathVariable long courseId) {
		return ResponseEntity.ok(courseStudentsService.addStudentsToCourse(courseId, StudentsId));
	}

	@Operation(description = "", summary = "get all students that include in specific course ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/{courseId}/students")
	public ResponseEntity<List<Student>> getStudentsBelongCourse(@PathVariable long courseId) {
		return ResponseEntity.ok(courseStudentsService.getStudentsBelongCourse(courseId));
	}
}
