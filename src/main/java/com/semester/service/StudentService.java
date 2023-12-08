package com.semester.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semester.Repository.MessageRepository;
import com.semester.Repository.QuizRepository;
import com.semester.Repository.StudentRepository;
import com.semester.domain.Message;
import com.semester.domain.Quiz;
import com.semester.domain.Student;
import com.semester.domain.Teacher;
import com.semester.dto.StudentRequest;
import com.semester.exceptions.NotFoundException;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	MessageRepository messageRepo;

	public Message saveStudent(StudentRequest studentRequest) {
		Student student = new Student();
		student.setFirstName(studentRequest.getFirstName());
		student.setLastName(studentRequest.getFirstName());
		student.setAddress(studentRequest.getAddress());
		student.setEmail(studentRequest.getEmail());
		student.setPhoneNumber(studentRequest.getPhoneNumber());
		studentRepo.save(student);
		return messageRepo.findByMessageNumber(7);// student is added
	}

	public Student getStudentById(long id) {
		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Course Found with " + id + " to show"));

		return student;
	}

	public Student updateStudentById(StudentRequest studentRequest, long id) {
		Student updateStudent = studentRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Student Found with " + id + " to updated"));
		;

		updateStudent.setFirstName(studentRequest.getFirstName());
		updateStudent.setLastName(studentRequest.getFirstName());
		updateStudent.setAddress(studentRequest.getAddress());
		updateStudent.setEmail(studentRequest.getEmail());
		updateStudent.setPhoneNumber(studentRequest.getPhoneNumber());

		studentRepo.save(updateStudent);

		return updateStudent; // return updated student
	}

	public Message deleteStudentById(long id) {
		try {
			studentRepo.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(" NO student Found with " + id + " to delete");
		}

		return messageRepo.findByMessageNumber(8);// student is deleted
	}

	public List<Student> getAllStudents() {
		List<Student> students = studentRepo.findAll();
		return students;
	}

	public List<Student> GetAllEmailMatchingStudents(String email) {
		List<Student> matchingStudents = studentRepo.findByEmailLikeIgnoreCase('%' + email + '%');
		return matchingStudents;
	}

}
