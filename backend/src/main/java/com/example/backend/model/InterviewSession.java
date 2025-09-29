package com.example.backend.model;

import java.util.List;


import javax.persistence.*;
@Entity

public class InterviewSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Candidate candidate;

    @ElementCollection
    private List<String> questions;
    @ElementCollection
    private List<String> answers;

    private int score;
    private String summary;
    private boolean completed;

    public Long getId() {
        return id;
    }       
    public void setId(Long id) {
        this.id = id;
    }
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    public List<String> getQuestions() {
        return questions;
    }
    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
    public List<String> getAnswers() {
        return answers;
    }
    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


}
