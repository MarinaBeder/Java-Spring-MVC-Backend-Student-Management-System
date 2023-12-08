package com.semester.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semester.domain.Message;
import com.semester.domain.Teacher;
import com.semester.dto.CourseRequest;
import com.semester.dto.TeacherRequest;
import com.semester.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Teacher RestController", description = "using @RestController")
@RestController
@RequestMapping("rest/v1/teacher")
public class TeacherRestController {
	@Autowired
	TeacherService teacherService;

	@Operation(description = "", summary = "To create teacher", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/")
	public ResponseEntity<Message> saveTeacher(@Valid @RequestBody TeacherRequest teacherRequest) {
		return ResponseEntity.ok(teacherService.saveTeacher(teacherRequest));
	}

	@Operation(description = "", summary = "To get specific teacher", responses = {
			@ApiResponse(description = "OK", responseCode = "200")}

	)
	@GetMapping("/{teacherId}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable long teacherId) {
		return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
	}

	@Operation(description = "", summary = "To delete specific teacher", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Message> deleteTeacherById(@PathVariable long teacherId) {
		return ResponseEntity.ok(teacherService.deleteTeacherById(teacherId));
	}

	@Operation(description = "", summary = "To update specific teacher", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PutMapping("/{teacherId}")
	public ResponseEntity<Teacher> updateTeacherById(@Valid @RequestBody TeacherRequest teacherRequest,
			@PathVariable long teacherId) {
		return ResponseEntity.ok(teacherService.updateTeacherById(teacherRequest, teacherId));
	}

	@Operation(description = "", summary = "To get all teachers", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/")
	public ResponseEntity<List<Teacher>> getAllTeachers() {
		return ResponseEntity.ok(teacherService.getAllTeachers());
	}

	@Operation(description = "user will put part of email and we will search about "
			+ "all teachers that matching this part of email", summary = "To search about teacher using part of email of teacher", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("search/{email}")
	public ResponseEntity<List<Teacher>> getAllEmailMatchingTeacher(@RequestParam String email) {
		return ResponseEntity.ok(teacherService.getAllEmailMatchingTeacher(email));
	}

}
