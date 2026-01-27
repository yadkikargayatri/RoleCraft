package com.rolecraft.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.controller.dto.ResumeTailorRequest;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;
import com.rolecraft.service.SkillMatchService;

@RestController
@RequestMapping("/api/resume")
public class ResumeTailorController {

    private final ResumeTailorService resumeTailorService;
    private final SkillMatchService skillMatchService;

    @Autowired
    public ResumeTailorController(
            ResumeTailorService resumeTailorService,
            SkillMatchService skillMatchService
    ) {
        this.resumeTailorService = resumeTailorService;
        this.skillMatchService = skillMatchService;
    }

    @PostMapping("/tailor")
    public ResponseEntity<TailoredResume> tailorResume(
            @RequestBody ResumeTailorRequest request
    ) {

        // 1. Extract required skills from job description
        Set<String> requiredSkills = new HashSet<>(
                request.getJobDescription().getRequiredSkills()
        );

        // 2. Match skills
        SkillMatchResult skillMatchResult =
                skillMatchService.match(requiredSkills, request.getResume());

        // 3. Tailor resume
        TailoredResume tailoredResume =
                resumeTailorService.tailorResume(
                        request.getResume(),
                        request.getJobDescription(),
                        skillMatchResult
                );

        return ResponseEntity.ok(tailoredResume);
    }
}
