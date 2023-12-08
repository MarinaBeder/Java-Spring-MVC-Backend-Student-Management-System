package com.semester.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semester.Repository.MessageRepository;
import com.semester.Repository.QuizRepository;
import com.semester.Repository.TeacherRepository;
import com.semester.domain.Message;
import com.semester.domain.Student;
import com.semester.domain.Teacher;
import com.semester.dto.CourseRequest;
import com.semester.dto.TeacherRequest;
import com.semester.exceptions.NotFoundException;

@Service
public class TeacherService {
	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	MessageRepository messageRepo;

	public Message saveTeacher(TeacherRequest teacherRequest) {
		Teacher teacher = new Teacher();
		teacher.setFirstName(teacherRequest.getFirstName());
		teacher.setLastName(teacherRequest.getFirstName());
		teacher.setAddress(teacherRequest.getAddress());
		teacher.setEmail(teacherRequest.getEmail());
		teacher.setPhoneNumber(teacherRequest.getPhoneNumber());

		teacherRepo.save(teacher);
		return messageRepo.findByMessageNumber(9);// teacher is added

	}

	public Teacher getTeacherById(long id) {
		Teacher teacher = teacherRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Course Found with " + id + " to show"));

		return teacher;
	}

	public Teacher updateTeacherById(TeacherRequest teacherRequest, long id) {
		Teacher updateTeacher = teacherRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("NO Course Found with " + id + " to show"));
		;

		updateTeacher.setFirstName(teacherRequest.getFirstName());
		updateTeacher.setLastName(teacherRequest.getFirstName());
		updateTeacher.setAddress(teacherRequest.getAddress());
		updateTeacher.setEmail(teacherRequest.getEmail());
		updateTeacher.setPhoneNumber(teacherRequest.getPhoneNumber());

		teacherRepo.save(updateTeacher);
		Message message = messageRepo.findByMessageNumber(3);
		message.setMessage(message.getMessage() + id);
		return updateTeacher; // return updatedteacher
	}

	public Message deleteTeacherById(long id) {
		try {
			teacherRepo.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException(" NO Course Found with " + id + " to delete");
		}

		return messageRepo.findByMessageNumber(10);// teacher is deleted
	}

	public List<Teacher> getAllTeachers() {
		List<Teacher> teachers = teacherRepo.findAll();
		return teachers;
	}

	public List<Teacher> getAllEmailMatchingTeacher(String email) {
		List<Teacher> matcingCourses = teacherRepo.findByEmailLikeIgnoreCase('%' + email + '%');
		return matcingCourses;
	}

}
