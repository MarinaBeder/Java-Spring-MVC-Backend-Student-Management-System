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
import com.semester.domain.Teacher;
import com.semester.dto.TeacherIdRequest;
import com.semester.dto.studentsIdRequest;
import com.semester.service.CourseTeachersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Course-Teachers RestController", description = "using @RestController")

@RestController
@RequestMapping("rest/v1/course")
public class CourseTeachersRestController {

	@Autowired
	CourseTeachersService courseTeachersService;

	@Operation(description = "", summary = "add List of teachers to course using course Id ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/{courseId}/teachers")
	public ResponseEntity<Message> addTeachersToCourse(@Valid @RequestBody List<TeacherIdRequest> teachersId,
			@PathVariable long courseId) {
		return ResponseEntity.ok(courseTeachersService.addTeachersToCourse(courseId, teachersId));
	}

}
