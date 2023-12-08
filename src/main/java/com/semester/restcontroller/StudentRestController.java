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
import com.semester.domain.Student;
import com.semester.dto.QuizRequest;
import com.semester.dto.StudentRequest;
import com.semester.dto.TeacherRequest;
import com.semester.service.StudentService;
import com.semester.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Student RestController", description = "using @RestController")
@RestController
@RequestMapping("rest/v1/student")
public class StudentRestController {
	@Autowired
	StudentService studentService;

	@Operation(description = "", summary = "To create student", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/")
	public ResponseEntity<Message> saveStudent(@Valid @RequestBody StudentRequest studentRequest) {
		return ResponseEntity.ok(studentService.saveStudent(studentRequest));
	}

	@Operation(description = "", summary = "To get specific student", responses = {
			@ApiResponse(description = "OK", responseCode = "200"),
			@ApiResponse(description = "Not Found", responseCode = "404") }

	)
	@GetMapping("/{studentId}")
	public ResponseEntity<Student> getStudentById(@PathVariable long studentId) {
		return ResponseEntity.ok(studentService.getStudentById(studentId));
	}

	@Operation(description = "", summary = "To delete specific student", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Message> deleteStudentById(@PathVariable long studentId) {
		return ResponseEntity.ok(studentService.deleteStudentById(studentId));
	}

	@Operation(description = "", summary = "To update specific student", responses = {
			@ApiResponse(description = "OK", responseCode = "200")}

	)
	@PutMapping("/{studentId}")
	public ResponseEntity<Student> updateStudentById(@Valid @RequestBody StudentRequest studentRequest,
			@PathVariable long studentId) {
		return ResponseEntity.ok(studentService.updateStudentById(studentRequest, studentId));
	}

	@Operation(description = "user will put part of email and we will search about "
			+ "all students that matching this part of email", summary = "To search about student using part of email of student", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("search/{email}")
	public ResponseEntity<List<Student>> GetAllEmailMatchingStudents(@RequestParam String email) {
		return ResponseEntity.ok(studentService.GetAllEmailMatchingStudents(email));
	}

}
