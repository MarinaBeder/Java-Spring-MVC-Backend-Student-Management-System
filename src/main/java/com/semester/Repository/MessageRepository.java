package com.semester.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semester.domain.Message;
import com.semester.domain.Quiz;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	Message findByMessageNumber(int messageNumber);
}
