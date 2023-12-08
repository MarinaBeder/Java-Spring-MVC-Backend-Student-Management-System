package com.semester.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semester.Repository.CourseRepository;
import com.semester.Repository.MessageRepository;
import com.semester.Repository.QuizRepository;
import com.semester.Repository.StudentRepository;
import com.semester.domain.Course;
import com.semester.domain.Message;
import com.semester.domain.Quiz;
import com.semester.domain.Student;
import com.semester.dto.studentsIdRequest;

@Service
public class QuizStudentsService {
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	QuizRepository quizRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	MessageRepository messageRepo;

	public Message addStudentsToQuiz(long quizId, List<studentsIdRequest> studentsId) {
		Set<Student> students = new HashSet<Student>();
		Quiz quiz = quizRepo.findById(quizId).get();
		for (studentsIdRequest studentId : studentsId) {
			students.add(studentRepo.findById(studentId.getStudentId()).get());
		}

		quiz.setStudent(students);
		quizRepo.save(quiz);
		return messageRepo.findByMessageNumber(6);// we add all these student to this quiz
	}

	public List<Student> getStudentsBelongQuiz(long quizId) {

		return studentRepo.findSudentByQuizes_Id(quizId);
	}

}
