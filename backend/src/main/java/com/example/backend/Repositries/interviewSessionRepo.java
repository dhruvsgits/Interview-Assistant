package com.example.backend.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.InterviewSession;

@Repository
public interface interviewSessionRepo extends JpaRepository<InterviewSession, Long> {

}
