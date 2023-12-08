package com.semester.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.semester.Repository.CourseRepository;
import com.semester.Repository.MessageRepository;
import com.semester.Repository.QuizRepository;
import com.semester.Repository.TeacherRepository;
import com.semester.domain.Course;
import com.semester.domain.Message;
import com.semester.domain.Quiz;
import com.semester.domain.Teacher;
import com.semester.dto.QuizRequest;
import com.semester.exceptions.NotFoundException;

@Service
public class QuizService {
	@Autowired
	QuizRepository quizRepo;

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	MessageRepository messageRepo;

	public Message saveQuiz(QuizRequest quizRequest, long courseId, long teacherId) {
		Quiz quiz = new Quiz();
		quiz.setName(quizRequest.getName());
		quiz.setGrade(quizRequest.getGrade());
		quiz.setStartQuiz(quizRequest.getStartQuiz());
		quiz.setEndQuiz(quizRequest.getEndQuiz());

		Teacher teacher = teacherRepo.findById(teacherId)
				.orElseThrow(() -> new NotFoundException("NO teacher Found with id " + teacherId + " to create quiz"));
		quiz.setTeacher(teacher);

		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new NotFoundException("NO course Found with id" + courseId + " to create quiz"));
		quiz.setCourse(course);
		quizRepo.save(quiz);

		return messageRepo.findByMessageNumber(5);// quiz is added
	}

	public Quiz getQuizById(long id) {
		Quiz quiz = quizRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Quiz Found with id" + id + " to show"));

		return quiz;
	}

	public Message deleteQuizById(long id) {
		try {
			quizRepo.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(" NO Quiz Found with id" + id + " to delete");
		}
		return messageRepo.findByMessageNumber(5);// quiz is deleted
	}

	public Quiz updateQuizById(QuizRequest quizRequest, long id) {
		Quiz updateQuiz = quizRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO quiz Found with id" + id + " to show"));
		;

		updateQuiz.setName(quizRequest.getName());
		updateQuiz.setGrade(quizRequest.getGrade());
		updateQuiz.setStartQuiz(quizRequest.getStartQuiz());
		updateQuiz.setEndQuiz(quizRequest.getEndQuiz());
		quizRepo.save(updateQuiz);

		return updateQuiz; // we return updated quiz
	}

	public List<Quiz> findAllQuizes() {
		return quizRepo.findAll();

	}

	public List<Quiz> GetAllWordMatchingQuizes(String searchWord) {
		List<Quiz> matchingQuizzes = quizRepo.findByNameLikeIgnoreCase('%' + searchWord + '%');
		return matchingQuizzes;
	}

}
