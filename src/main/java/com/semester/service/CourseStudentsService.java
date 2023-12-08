package com.semester.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semester.Repository.CourseRepository;
import com.semester.Repository.MessageRepository;
import com.semester.Repository.StudentRepository;
import com.semester.domain.Course;
import com.semester.domain.Message;
import com.semester.domain.Student;
import com.semester.dto.studentsIdRequest;

@Service
public class CourseStudentsService {
	@Autowired
	CourseRepository courseRepo;

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	MessageRepository messageRepo;

	public Message addStudentsToCourse(long courseId, List<studentsIdRequest> studentsId) {
		Set<Student> students = new HashSet<Student>();
		Course course = courseRepo.findById(courseId).get();
		for (studentsIdRequest studentId : studentsId) {
			students.add(studentRepo.findById(studentId.getStudentId()).get());
		}

		course.setStudents(students);
		courseRepo.save(course);
		return messageRepo.findByMessageNumber(3);// we add all these student to this course
	}

	public List<Student> getStudentsBelongCourse(long courseId) {

		return studentRepo.findSudentByCourses_Id(courseId);
	}

}
