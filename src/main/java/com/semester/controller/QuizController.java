package com.semester.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semester.domain.Course;
import com.semester.domain.Quiz;
import com.semester.dto.CourseRequest;
import com.semester.dto.QuizRequest;
import com.semester.dto.TeacherRequest;
import com.semester.service.QuizService;
import com.semester.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "QuizController", description = "using @Controller")
@Controller
@RequestMapping("/v1/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;

	@Operation(description = "", summary = "To get all quizzes", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/all")
	public String getAllQuizes(Model model) {
		List<Quiz> quizes = quizService.findAllQuizes();
		model.addAttribute("quizes", quizes);
		return "quizzes-list";
	}

	@Operation(description = "", summary = "To get form", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/form/create")
	public String saveQuizForm(Model model) {
		QuizRequest quizRequest = new QuizRequest();
		long courseId = 0;
		long teacherId = 0;
		model.addAttribute("quiz", quizRequest);
		model.addAttribute("courseId", courseId);
		model.addAttribute("teacherId", teacherId);

		return "quiz-create-form";
	}

	@Operation(description = "when user add quiz and do submission "
			+ "then we will create quiz then go to page of the list of quizzes", summary = "To create new quiz", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/{courseId}/{teacherId}")
	public String saveQuiz(@Valid @ModelAttribute("quiz") QuizRequest quizRequest,
			@PathVariable("courseId") long courseId, @PathVariable("teacherId") long teacherId, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			model.addAttribute("quiz", quizRequest);
			model.addAttribute("courseId", courseId);
			model.addAttribute("teacherId", teacherId);
			return "quiz-ceate-form";
		}
		System.out.println("Quiz Request is " + quizRequest);
		quizService.saveQuiz(quizRequest, courseId, teacherId);

		return "redirect:/v1/quiz/all";

	}

	@Operation(description = "", summary = "Form to edit /update quiz", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("form/update/{quizId}")
	public String UpdateQuizForm(@PathVariable("quizId") long quizId, Model model) {
		Quiz quiz = quizService.getQuizById(quizId);
		model.addAttribute("quiz", quiz);
		return "quiz-update-form";
	}

	@Operation(description = "when user submit (his/her) updating\""
			+ "then we will update quiz then go to page of the list of quizzes",

			summary = "To edit /update quizzes", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/{quizId}")
	public String updateQuizById(@PathVariable("quizId") long quizId,
			@Valid @ModelAttribute("quiz") QuizRequest quizRequest, BindingResult result)

	{
		if (result.hasErrors()) {
			return "quiz-update-form";
		}

		quizService.updateQuizById(quizRequest, quizId);
		return "redirect:/v1/quiz/all";
	}

	/*******************
	 * 
	 */
	@Operation(description = "",

			summary = "to get specific quiz", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/{quizId}")
	public String getQuizById(@PathVariable("quizId") long quizId, Model model) {
		Quiz quiz = quizService.getQuizById(quizId);
		model.addAttribute("quiz", quiz);
		return "quiz-detail";
	}

	@Operation(description = "to delete quiz",

			summary = "To delete quiz", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("delete/{quizId}")
	public String deleteQuizById(@PathVariable("quizId") long quizId) {
		quizService.deleteQuizById(quizId);
		return "redirect:/v1/quiz/all";
	}

	@Operation(description = "we make search bar in page of quizzes list " + " \n user can put (his/her) word "
			+ "and we will search about this quizzes that matching this word ",

			summary = "search about matching quiz", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("matching/{searching-word}")
	public String GetAllWordMatchingQuizes(@RequestParam String searchingWord, Model model) {

		List<Quiz> quizzes = quizService.GetAllWordMatchingQuizes(searchingWord);
		model.addAttribute("quizzes", quizzes);
		return "quizzes-list";
	}

}
