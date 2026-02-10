package com.rolecraft.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolecraft.ai.service.AIRecommendationService;
import com.rolecraft.controller.dto.ResumeTailorRequest;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;
import com.rolecraft.service.SkillMatchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/internal/resume")
public class ResumeController {

    private final ResumeTailorService resumeTailorService;
    private final SkillMatchService skillMatchService;
    private final AIRecommendationService aiRecommendationService;

    public ResumeController(
            ResumeTailorService resumeTailorService,
            SkillMatchService skillMatchService,
            AIRecommendationService aiRecommendationService) {
        this.resumeTailorService = resumeTailorService;
        this.skillMatchService = skillMatchService;
        this.aiRecommendationService = aiRecommendationService;
    }

    @PostMapping("/tailor")
    public ResponseEntity<TailoredResume> tailorResume(
            @Valid @RequestBody ResumeTailorRequest request) {

        // 1️⃣ Skill matching
        Set<String> requiredSkills =
                new HashSet<>(request.getJobDescription().getRequiredSkills());

        SkillMatchResult skillMatchResult =
                skillMatchService.matchSkills(
                        requiredSkills,
                        request.getResume().getSkills(),
                        new HashSet<>()
                );

        // 2️⃣ Tailor resume
        TailoredResume tailoredResume =
                resumeTailorService.tailorResume(
                        request.getResume(),
                        request.getJobDescription()
                       
                );

        // 3️⃣ AI Suggestions
        List<String> suggestions =
                aiRecommendationService.suggestImprovements(
                        request.getResume(),
                        request.getJobDescription(),
                        skillMatchResult
                );

        tailoredResume.setAiSuggestions(suggestions);

        return ResponseEntity.ok(tailoredResume);
    }
}
