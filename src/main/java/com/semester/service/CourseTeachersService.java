package com.semester.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semester.Repository.CourseRepository;
import com.semester.Repository.MessageRepository;
import com.semester.Repository.StudentRepository;
import com.semester.Repository.TeacherRepository;
import com.semester.domain.Course;
import com.semester.domain.Message;
import com.semester.domain.Student;
import com.semester.domain.Teacher;
import com.semester.dto.TeacherIdRequest;
import com.semester.dto.studentsIdRequest;

@Service
public class CourseTeachersService {
	@Autowired
	CourseRepository courseRepo;

	@Autowired
	TeacherRepository teacherRepo;

	@Autowired
	MessageRepository messageRepo;

	public Message addTeachersToCourse(long courseId, List<TeacherIdRequest> teachersId) {
		Set<Teacher> teachers = new HashSet<Teacher>();
		Course course = courseRepo.findById(courseId).get();
		for (TeacherIdRequest teacherId : teachersId) {
			teachers.add(teacherRepo.findById(teacherId.getTeacherId()).get());
		}

		course.setTeacher(teachers);
		courseRepo.save(course);
		return messageRepo.findByMessageNumber(4);// we add these teachers into this course
	}

}
