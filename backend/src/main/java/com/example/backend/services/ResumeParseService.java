package com.example.backend.services;

import java.io.IOException;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.Candidate;

@Service
public class ResumeParseService {
    @Autowired
  private AiService aiService;

  public Candidate parseResume(MultipartFile file) throws IOException, TikaException {
    Tika tika = new Tika();
    String text = tika.parseToString(file.getInputStream());
    return aiService.parseResumeWithAI(text);
  }

}
