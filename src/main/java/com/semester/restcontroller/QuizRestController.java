package com.semester.restcontroller;

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

import com.semester.dto.QuizRequest;
import com.semester.dto.TeacherRequest;
import com.semester.service.QuizService;
import com.semester.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Quiz RestController", description = "using @RestController")
@RestController
@RequestMapping("rest/v1/quiz")
public class QuizRestController {
	@Autowired
	QuizService quizService;

	@Operation(description = "we need which course will belong to this quiz "
			+ "and who create this quiz", summary = "To create new quiz", responses = {
					@ApiResponse(description = "OK", responseCode = "200") })
	@PostMapping("/{courseId}/{teacherId}")
	public ResponseEntity<?> saveQuiz(@Valid @RequestBody QuizRequest quizRequest, @PathVariable long courseId,
			@PathVariable long teacherId) {
		return ResponseEntity.ok(quizService.saveQuiz(quizRequest, courseId, teacherId));
	}

	@Operation(description = "", summary = "To get specific quiz", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/{quizId}")
	public ResponseEntity<?> getQuizById(@PathVariable long quizId) {
		return ResponseEntity.ok(quizService.getQuizById(quizId));
	}

	@Operation(description = "", summary = "To delete specific quiz", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@DeleteMapping("/{quizId}")
	public ResponseEntity<?> deleteQuizById(@PathVariable long quizId) {
		return ResponseEntity.ok(quizService.deleteQuizById(quizId));
	}

	@Operation(description = "", summary = "To update specific quiz", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PutMapping("/{quizId}")
	public ResponseEntity<?> updateQuizById(@Valid @RequestBody QuizRequest quizRequest, @PathVariable long quizId) {
		return ResponseEntity.ok(quizService.updateQuizById(quizRequest, quizId));
	}

	@Operation(description = "user will put part of quiz name and we will search about "
			+ "all quizzes that matching this word", summary = "To search about quiz using part of quiz name", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("search/{searching-word}")
	public ResponseEntity<?> getAllWordMatchingQuizes(@RequestParam String searchingWord) {
		return ResponseEntity.ok(quizService.GetAllWordMatchingQuizes(searchingWord));
	}

}
