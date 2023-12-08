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

import com.semester.dto.studentsIdRequest;
import com.semester.service.QuizStudentsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Quiz-Students RestController", description = "using @RestController")

@RestController
@RequestMapping("rest/v1/quiz")
public class QuizStudentsRestController {
	@Autowired
	QuizStudentsService quizStudentsService;

	@Operation(description = "", summary = "add List of students to quiz using quiz Id ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/{quizId}/students")
	public ResponseEntity<?> addStudentsToQuiz(@Valid @RequestBody List<studentsIdRequest> studentsIdRequest,
			@PathVariable long quizId) {
		return ResponseEntity.ok(quizStudentsService.addStudentsToQuiz(quizId, studentsIdRequest));
	}

	@Operation(description = "", summary = "get List of students that belong to quiz using quiz Id ", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/{quizId}/students")
	public ResponseEntity<?> getStudentsBelongQuiz(@PathVariable long quizId) {
		return ResponseEntity.ok(quizStudentsService.getStudentsBelongQuiz(quizId));
	}
}
