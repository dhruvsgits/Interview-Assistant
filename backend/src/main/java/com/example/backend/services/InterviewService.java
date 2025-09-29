package com.example.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Repositries.candidaterepository;
import com.example.backend.Repositries.interviewSessionRepo;
import com.example.backend.model.Candidate;
import com.example.backend.model.InterviewSession;

@Service
public class InterviewService {
    @Autowired
  private candidaterepository candidateRepo;

  @Autowired
  private interviewSessionRepo sessionRepo;

  @Autowired
  private AiService aiService;

  public InterviewSession startInterview(Candidate candidate, String role, String level) {
    candidate = candidateRepo.save(candidate);
    List<String> questions = aiService.generateQuestions(role, level);

    InterviewSession session = new InterviewSession();
    session.setCandidate(candidate);
    session.setQuestions(questions);
    session.setAnswers(new ArrayList<>());
    session.setScore(0);
    session.setCompleted(false);

    return sessionRepo.save(session);
  }

  public InterviewSession submitAnswer(Long sessionId, String answer) {
    InterviewSession session = sessionRepo.findById(sessionId).orElseThrow(null);
    int idx = session.getAnswers().size();
    session.getAnswers().add(answer);

    int score = aiService.evaluateAnswer(session.getQuestions().get(idx), answer);
    session.setScore(session.getScore() + score);

    if (session.getAnswers().size() == session.getQuestions().size()) {
      session.setCompleted(true);
      session.setSummary("Interview completed. Final score: " + session.getScore());
    }

    return sessionRepo.save(session);
  }
}
