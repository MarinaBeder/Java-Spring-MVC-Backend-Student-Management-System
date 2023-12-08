package com.semester.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semester.domain.Course;
import com.semester.domain.Student;
import com.semester.domain.Teacher;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByEmailLikeIgnoreCase(String email);

	List<Student> findSudentByCourses_Id(long courseId);

	List<Student> findSudentByQuizes_Id(long quizId);

	List<Student> findSudentByTeachers_Id(long teacherId);

}
