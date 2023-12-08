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

import com.semester.dto.TeacherIdRequest;
import com.semester.dto.studentsIdRequest;
import com.semester.service.CourseTeachersService;
import com.semester.service.TeacherStudentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Teacher-Students RestController", description = "using @RestController")

@RestController
@RequestMapping("rest/v1/teacher")
public class TeacherStudentsRestController {
	@Autowired
	TeacherStudentsService teacherStudentsService;

	@Autowired
	CourseTeachersService courseTeachersService;

	@Operation(description = "", summary = "add List of students to teacher using teacher Id ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/{teacherId}/students")
	public ResponseEntity<?> addStudentsToTeacher(@Valid @RequestBody List<studentsIdRequest> studentsIdRequest,
			@PathVariable long teacherId) {
		return ResponseEntity.ok(teacherStudentsService.addStudentsToTeacher(teacherId, studentsIdRequest));
	}

	@Operation(description = "", summary = "get List of students that belong to teacher using teacher Id ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/{teacherId}/students")
	public ResponseEntity<?> getStudentsBelongTeacher(@PathVariable long teacherId) {
		return ResponseEntity.ok(teacherStudentsService.getStudentsBelongTeacher(teacherId));
	}
}
