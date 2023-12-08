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

import com.semester.domain.Quiz;
import com.semester.domain.Student;
import com.semester.domain.Teacher;
import com.semester.dto.CourseRequest;
import com.semester.dto.QuizRequest;
import com.semester.dto.StudentRequest;
import com.semester.dto.TeacherRequest;
import com.semester.service.StudentService;
import com.semester.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Student Controller", description = "using @Controller")
@Controller
@RequestMapping("/v1/student")
public class StudentController {
	@Autowired
	StudentService studentService;

	@Operation(description = "", summary = "To get all students", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/all")
	public String getAllStudents(Model model) {
		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		return "students-list";
	}

	@Operation(description = "", summary = "To get form", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("/form/create")
	public String saveStudentForm(Model model) {
		StudentRequest studentRequest = new StudentRequest();
		model.addAttribute("student", studentRequest);

		return "student-create-form";
	}

	@Operation(description = "when user add student and do submission "
			+ "then we will create student then go to page of the list of students", summary = "To create new student", responses = {
					@ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("/create")
	public String saveStudent(@Valid @ModelAttribute("student") StudentRequest studentRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			model.addAttribute("student", studentRequest);
			return "student-create-form";
		}

		studentService.saveStudent(studentRequest);

		return "redirect:/v1/student/all";

	}

	@Operation(description = "", summary = "Form to edit /update student", responses = {
			@ApiResponse(description = "OK", responseCode = "200") }

	)
	@GetMapping("form/update/{studentId}")
	public String UpdateStudentForm(@PathVariable("studentId") long studentId, Model model) {
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "student-update-form";
	}

	@Operation(description = "when user submit (his/her) updating\"" + "then go to page of the list of student",

			summary = "To edit /update student", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)
	@PostMapping("update/{studentId}")
	public String updateStudentById(@PathVariable("studentId") long studentId,
			@Valid @ModelAttribute("student") StudentRequest studentRequest, BindingResult result)

	{
		if (result.hasErrors()) {
			return "student-update-form";
		}

		studentService.updateStudentById(studentRequest, studentId);
		return "redirect:/v1/student/all";
	}

	@Operation(description = "",

			summary = "to get specific student", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("/{studentId}")
	public String getStudentById(@PathVariable("studentId") long studentId, Model model) {
		Student student = studentService.getStudentById(studentId);
		model.addAttribute("student", student);
		return "student-detail";
	}

	@Operation(description = "to delete student",

			summary = "To delete student", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("delete/{studentId}")
	public String deletestudentById(@PathVariable("studentId") long studentId) {
		studentService.deleteStudentById(studentId);
		return "redirect:/v1/student/all";
	}

	@Operation(description = "we make search bar in page of student list " + " \n user can put (his/her) word "
			+ "and we will search about this student that matching this word ",

			summary = "To search about student", responses = { @ApiResponse(description = "OK", responseCode = "200") }

	)

	@GetMapping("matching/{email}")
	public String GetAllWordMatchingStudents(@RequestParam String email, Model model) {

		List<Student> students = studentService.GetAllEmailMatchingStudents(email);
		model.addAttribute("students", students);
		return "students-list";
	}
}
