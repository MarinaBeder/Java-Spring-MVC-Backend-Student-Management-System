package com.semester.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semester.domain.Course;
import com.semester.domain.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	List<Quiz> findByNameLikeIgnoreCase(String name);

}
