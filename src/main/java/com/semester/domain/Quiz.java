package com.semester.domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private LocalDateTime startQuiz;
	private LocalDateTime endQuiz;
	private float grade;

	@ManyToOne
	private Course course;
	@ManyToOne
	private Teacher teacher;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY) 
	@JoinTable(name = "quiz_student", joinColumns = @JoinColumn(name = "quiz_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	Set<Student> student;

}
