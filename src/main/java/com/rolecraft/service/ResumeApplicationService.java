package com.rolecraft.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rolecraft.ai.service.AIRecommendationService;
import com.rolecraft.controller.dto.ResumeTailorRequest;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.repository.TailoredResumeRepository;

import jakarta.transaction.Transactional;

@Service
public class ResumeApplicationService {

    private final ResumeTailorService resumeTailorService;
    private final SkillMatchService skillMatchService;
    private final AIRecommendationService aiRecommendationService;
    private final TailoredResumeRepository repository;

    public ResumeApplicationService(
            ResumeTailorService resumeTailorService,
            SkillMatchService skillMatchService,
            AIRecommendationService aiRecommendationService,
            TailoredResumeRepository repository) {
        this.resumeTailorService = resumeTailorService;
        this.skillMatchService = skillMatchService;
        this.aiRecommendationService = aiRecommendationService;
        this.repository = repository;
    }

    @Transactional
    public TailoredResume tailorResume(ResumeTailorRequest request) {

        Set<String> requiredSkills = new HashSet<>(request.getJobDescription().getRequiredSkills());

        SkillMatchResult skillMatchResult = skillMatchService.matchSkills(
                requiredSkills,
                request.getResume().getSkills(),
                new HashSet<>()
        );

        TailoredResume tailoredResume = resumeTailorService.tailorResume(
                request.getResume(),
                request.getJobDescription()
        );

        List<String> suggestions = aiRecommendationService.suggestImprovements(
                request.getResume(),
                request.getJobDescription(),
                skillMatchResult
        );

        tailoredResume.setAiSuggestions(suggestions);

        repository.save(tailoredResume);

        return tailoredResume;
    }
}

