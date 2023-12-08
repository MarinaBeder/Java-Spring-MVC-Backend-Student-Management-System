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
import com.semester.domain.Teacher;
import com.semester.dto.CourseRequest;
import com.semester.dto.TeacherRequest;
import com.semester.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Teacher Controller", description = "using @Controller")

@Controller
@RequestMapping("/v1/teacher")
public class TeacherController {
	@Autowired
	TeacherService teacherService;

	@Operation(description = "", summary = "To get all quizes", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/all")
	public String getAllTeachers(Model model) {
		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("teachers", teachers);
		return "teachers-list";
	}

	@Operation(description = "", summary = "To get form", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/form/create")
	public String saveTeacherForm(Model model) {
		TeacherRequest teacherRequest = new TeacherRequest();
		model.addAttribute("teacher", teacherRequest);

		return "teacher-create-form";
	}

	@Operation(description = "when user add teacher and do submission "
			+ "then we will create teacher then go to page of the list of teachers", summary = "To create new course", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/create")
	public String saveTeacher(@Valid @ModelAttribute("teacher") TeacherRequest teacherRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			model.addAttribute("teacher", teacherRequest);
			return "teacher-create-form";
		}
		System.out.println("Course Request is " + teacherRequest);
		teacherService.saveTeacher(teacherRequest);

		return "redirect:/v1/teacher/all";

	}

	@Operation(description = "", summary = "Form to edit /update teacher", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("form/update/{teacherId}")
	public String UpdateTeacherForm(@PathVariable("teacherId") long teacherId, Model model) {
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "teacher-update-form";
	}

	@Operation(description = "when user submit (his/her) updating\""
			+ "then we will update teacher then go to page of the list of teachers",

			summary = "To edit /update teacher", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("update/{teacherId}")
	public String updateTeacherById(@PathVariable("teacherId") long teacherId,
			@Valid @ModelAttribute("teacher") TeacherRequest teacherRequest, BindingResult result)

	{
		System.out.println("Course Request is " + teacherRequest);
		if (result.hasErrors()) {
			return "teacher-update-form";
		}

		teacherService.updateTeacherById(teacherRequest, teacherId);
		return "redirect:/v1/teacher/all";
	}

	@Operation(description = "",

			summary = "to get specific teacher", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/{teacherId}")
	public String getTeacherById(@PathVariable("teacherId") long teacherId, Model model) {
		Teacher teacher = teacherService.getTeacherById(teacherId);
		model.addAttribute("teacher", teacher);
		return "teacher-detail";
	}

	@Operation(description = "to delete teacher",

			summary = "To delete teacher", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("delete/{teacherId}")
	public String deleteTeacherById(@PathVariable("teacherId") long teacherId) {
		teacherService.deleteTeacherById(teacherId);
		return "redirect:/v1/teacher/all";
	}

	@Operation(description = "we make search bar in page of courses list " + " \n user can put (his/her) word "
			+ "and we will search about this teachers that matching this word ",

			summary = "To search about teachers who maching part of email ", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("matching/{email}")
	public String getAllEmailMatchingTeacher(@RequestParam String email, Model model) {

		List<Teacher> teachers = teacherService.getAllEmailMatchingTeacher(email);
		model.addAttribute("teachers", teachers);
		return "teachers-list";
	}

}
