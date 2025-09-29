package com.example.backend.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Candidate;

@Repository
public interface candidaterepository extends JpaRepository<Candidate, Long> {

}
