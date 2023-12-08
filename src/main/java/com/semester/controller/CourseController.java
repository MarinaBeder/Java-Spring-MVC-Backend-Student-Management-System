package com.semester.controller;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semester.domain.Course;
import com.semester.dto.CourseRequest;
import com.semester.exceptions.NotFoundException;
import com.semester.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Course Controller", description = "using @Controller")

@Controller
@RequestMapping("/v1/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@Operation(description = "", summary = "To get all courses", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/all")
	public String getAllCourses(Model model) {
		List<Course> courses = courseService.findAllCourses();
		model.addAttribute("courses", courses);
		return "courses-list";
	}

	@Operation(description = "", summary = "To get form", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/form/create")
	public String saveCourseForm(Model model) {
		CourseRequest courseRequest = new CourseRequest();
		model.addAttribute("course", courseRequest);

		return "course-create-form";
	}

	@Operation(description = "when user add course and do submission "
			+ "then we will create course then go to page of the list of courses", summary = "To create new course", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/create")
	public String saveCourse(@Valid @ModelAttribute("course") CourseRequest courseRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			model.addAttribute("course", courseRequest);
			return "course-create-form";
		}
		// System.out.println("Course Request is "+courseRequest);
		courseService.saveCourse(courseRequest);

		return "redirect:/v1/course/all";

	}

	@Operation(description = "", summary = "Form to edit /update course", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("form/update/{courseId}")
	public String UpdateCourseForm(@PathVariable("courseId") long courseId, Model model) {
		Course course = courseService.getCourseById(courseId);
		model.addAttribute("course", course);
		return "course-update-form";
	}

	@Operation(description = "when user submit (his/her) updating\""
			+ "then we will update course then go to page of the list of courses",

			summary = "To edit /update course", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("update/{courseId}")
	public String updateCourseById(@PathVariable("courseId") long courseId,
			@Valid @ModelAttribute("course") CourseRequest courseRequest, BindingResult result)

	{ // System.out.println("Course Request is "+courseRequest);
		if (result.hasErrors()) {
			return "course-update-form";
		}

		courseService.updateCourseById(courseRequest, courseId);
		return "redirect:/v1/course/all";
	}

	@Operation(description = "",

			summary = "to get specific course", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/{courseId}")
	public String getCourseById(@PathVariable("courseId") long courseId, Model model) {
		Course course = courseService.getCourseById(courseId);
		model.addAttribute("course", course);
		return "course-detail";
	}

	@Operation(description = "to delete course",

			summary = "To delete course", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("delete/{courseId}")
	public String deleteCourseById(@PathVariable("courseId") long courseId) {
		courseService.deleteCourseById(courseId);
		return "redirect:/v1/course/all";
	}

	@Operation(description = "we make search bar in page of courses list " + " \n user can put (his/her) word "
			+ "and we will search about this courses that matching this word ",

			summary = "To search about courses using course name", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("matching/{searching-word}")
	public String GetAllWordMatchingCourses(@RequestParam String searchingWord, Model model) {

		List<Course> courses = courseService.GetAllWordMatchingCourses(searchingWord);
		model.addAttribute("courses", courses);
		return "courses-list";
	}
}
