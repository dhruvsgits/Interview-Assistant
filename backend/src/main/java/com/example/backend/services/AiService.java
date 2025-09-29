package com.example.backend.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.backend.model.Candidate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class AiService {
    @Value("${prepai.api.url}")
    private String prepaiApiUrl;  

    @Value("${prepai.api.key}")
    private String prepaiApiKey;

    private final RestTemplate restTemplate;

    public AiService() {
        this.restTemplate = new RestTemplate();
    }

    
    public List<String> generateQuestions(String role, String level) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", prepaiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("text", "Generate 2 easy, 2 medium, and 2 hard interview questions for a " + level + " " + role + " developer.");
        body.put("max_questions", 6);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(prepaiApiUrl, request, String.class);

        return parseQuestionsFromResponse(response.getBody());
    }

    private List<String> parseQuestionsFromResponse(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(jsonResponse);
            JsonNode questionsNode = root.path("questions"); // adapt key according to API
            if (questionsNode.isMissingNode() || !questionsNode.isArray()) {
                return Collections.emptyList();
            }
            List<String> questions = new ArrayList<>();
            for (JsonNode q : questionsNode) {
                questions.add(q.asText());
            }
            return questions;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public int evaluateAnswer(String question, String answer) {
        return 8;
    }


    public Candidate parseResumeWithAI(String resumeText) {
        Candidate candidate = new Candidate();
        candidate.setName(extractPattern(resumeText, "Name:\\s*(.*)"));
        candidate.setEmail(extractPattern(resumeText, "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b"));
        candidate.setPhoneno(extractPattern(resumeText, "\\b\\d{10}\\b"));
        return candidate;
    }

    private String extractPattern(String text, String pattern) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        java.util.regex.Matcher m = p.matcher(text);
        if (m.find()) {
            return m.group(1).trim();
        }
        return "";
    }


}
