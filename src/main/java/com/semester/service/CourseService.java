package com.semester.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semester.Repository.CourseRepository;
import com.semester.Repository.MessageRepository;
import com.semester.domain.Course;
import com.semester.domain.Message;
import com.semester.dto.CourseRequest;
import com.semester.dto.QuizRequest;
import com.semester.exceptions.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	MessageRepository messageRepo;

	public Message saveCourse(CourseRequest courseRequest) {

		Course course = new Course();
		course.setName(courseRequest.getName());
		course.setCost(courseRequest.getCost());
		course.setDuration(courseRequest.getDuration());
		courseRepo.save(course);

		return messageRepo.findByMessageNumber(1);// we save this message in DataBase
	}

	public Course getCourseById(long id) {
		Course getCourse = courseRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Course Found with " + id + " to show"));

		return getCourse;

	}

	public Message deleteCourseById(Long id) {

		try {
			courseRepo.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(" NO Course Found with " + id + " to delete");
		}

		return messageRepo.findByMessageNumber(2);// this course is deleted
	}

	public Course updateCourseById(CourseRequest courseRequest, long id) {
		Course updateCourse = courseRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Course Found with " + id + " to show"));
		;

		updateCourse.setCost(courseRequest.getCost());
		updateCourse.setDuration(courseRequest.getDuration());
		updateCourse.setName(courseRequest.getName());
		courseRepo.save(updateCourse);
		return updateCourse; // return updated course

	}

	public List<Course> findAllCourses() {
		return courseRepo.findAll();

	}

	public List<Course> GetAllWordMatchingCourses(String searchWord) {
		List<Course> matchingCourses = courseRepo.findByNameLikeIgnoreCase('%' + searchWord + '%');
		return matchingCourses;
	}

}
