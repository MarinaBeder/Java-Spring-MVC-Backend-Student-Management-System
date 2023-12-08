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
import com.semester.domain.Student;
import com.semester.domain.Teacher;
import com.semester.dto.TeacherIdRequest;
import com.semester.dto.studentsIdRequest;

@Service
public class TeacherStudentsService {
	@Autowired
	CourseRepository courseRepo;

	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	MessageRepository messageRepo;

	public Object addStudentsToTeacher(long teacherId, List<studentsIdRequest> studentsIdRequest) {
		Teacher teacher = teacherRepo.findById(teacherId).get();
		Set<Student> students = new HashSet<Student>();

		for (studentsIdRequest studentId : studentsIdRequest) {
			students.add(studentRepo.findById(studentId.getStudentId()).get());
		}
		teacher.setStudents(students);
		teacherRepo.save(teacher);
		return messageRepo.findByMessageNumber(11);// we add these student into this teacher
	}

	public List<Student> getStudentsBelongTeacher(long teacherId) {

		return studentRepo.findSudentByTeachers_Id(teacherId);
	}

}
