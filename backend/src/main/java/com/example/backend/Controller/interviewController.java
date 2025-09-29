package com.example.backend.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Candidate;
import com.example.backend.model.InterviewSession;
import com.example.backend.services.InterviewService;

@RestController
@RequestMapping("/interview")

public class interviewController {
    @Autowired
  private InterviewService interviewService;

  @PostMapping("/start")
  public InterviewSession startInterview(@RequestBody Candidate candidate) {
    return interviewService.startInterview(candidate, "Fullstack Developer", "Intermediate");
  }

  @PostMapping("/{id}/submit")
  public InterviewSession submitAnswer(@PathVariable Long id, @RequestBody Map<String, String> body) {
    String answer = body.get("answer");
    return interviewService.submitAnswer(id, answer);
  }

}
