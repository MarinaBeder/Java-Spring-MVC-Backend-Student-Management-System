package com.semester.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.semester.domain.Course;
import com.semester.domain.Student;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByNameLikeIgnoreCase(String name);
}
