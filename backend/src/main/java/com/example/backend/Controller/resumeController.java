package com.example.backend.Controller;

import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.Candidate;
import com.example.backend.services.ResumeParseService;

@RestController
@RequestMapping("/resume")
public class resumeController {
    @Autowired
    private ResumeParseService resumeParseService;

  @PostMapping("/upload")
  public Candidate uploadResume(@RequestParam("file") MultipartFile file) throws IOException, TikaException {
    return resumeParseService.parseResume(file);
  } 


}
