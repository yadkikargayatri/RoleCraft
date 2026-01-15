package com.rolecraft.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rolecraft.model.Resume;
import com.rolecraft.service.ResumeService;




@RestController@RequestMapping("/api/resume")
public class ResumeController { 

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(value = "/parse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Resume parseResume(@RequestParam("file") MultipartFile file) {
        return resumeService.parseResume(file);
    }
}