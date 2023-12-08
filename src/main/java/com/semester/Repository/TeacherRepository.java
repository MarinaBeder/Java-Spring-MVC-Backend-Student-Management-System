package com.semester.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semester.domain.Course;
import com.semester.domain.Student;
import com.semester.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	List<Teacher> findByEmailLikeIgnoreCase(String email);

}
