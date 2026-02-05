package com.rolecraft.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/resume")
public class ResumeTailorController {

    private final ResumeTailorService resumeTailorService;
    private final SkillMatchService skillMatchService;
    private final AIRecommendationService aiRecommendationService;

    @Autowired
    public ResumeTailorController(
            ResumeTailorService resumeTailorService,
            SkillMatchService skillMatchService,
            AIRecommendationService aiRecommendationService) {
        this.resumeTailorService = resumeTailorService;
        this.skillMatchService = skillMatchService;
        this.aiRecommendationService = aiRecommendationService;
    }

    @PostMapping("/tailor")
    public ResponseEntity<TailoredResume> tailorResume(@RequestBody ResumeTailorRequest request) {

        try {
            // 1️⃣ Extract required skills from job description
            Set<String> requiredSkills = new HashSet<>(request.getJobDescription().getRequiredSkills());

            // 2️⃣ Match skills
            SkillMatchResult skillMatchResult = skillMatchService.matchSkills(
                    requiredSkills,
                    request.getResume().getSkills(),
                    new HashSet<>()
            );

            // 3️⃣ Tailor resume
            TailoredResume tailoredResume = resumeTailorService.tailorResume(
                    request.getResume(),
                    request.getJobDescription(),
                    skillMatchResult
            );

            // 4️⃣ AI recommendations
            List<String> suggestions = aiRecommendationService.suggestImprovements(
                    request.getResume(),
                    request.getJobDescription(),
                    skillMatchResult
            );
            tailoredResume.setAiSuggestions(suggestions);

            return ResponseEntity.ok(tailoredResume);

        } catch (Exception e) {
            // Print full stack trace to console for debugging
            e.printStackTrace();

            // Return generic 500 response with error message
            return ResponseEntity.status(500)
                    .body(null);
        }
    }
}
