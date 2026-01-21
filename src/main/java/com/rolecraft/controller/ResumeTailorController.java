package com.rolecraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.controller.dto.ResumeTailorRequest;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;

@RestController
@RequestMapping("/api/resume")
public class ResumeTailorController {

    private final ResumeTailorService resumeTailorService;

    @Autowired
    public ResumeTailorController(ResumeTailorService resumeTailorService) {
        this.resumeTailorService = resumeTailorService;
    }

    @PostMapping("/tailor")
    public ResponseEntity<TailoredResume> tailorResume(
            @RequestBody ResumeTailorRequest request) {

        TailoredResume tailoredResume = resumeTailorService.tailorResume(
                request.getResume(),
                request.getJobDescription(),
                request.getSkillMatchResult()
        );

        return ResponseEntity.ok(tailoredResume);
    }
}
