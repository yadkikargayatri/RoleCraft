package com.rolecraft.service.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rolecraft.ai.service.AIRecommendationService;
import com.rolecraft.ai.service.SkillExtractionService;
import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;
import com.rolecraft.service.SkillMatchService;

@Service
public class ResumeTailorServiceImpl implements ResumeTailorService {

    private final SkillExtractionService skillExtractionService;
    private final SkillMatchService skillMatchService;
    private final AIRecommendationService aiRecommendationService;

    @Autowired
    public ResumeTailorServiceImpl(
            SkillExtractionService skillExtractionService,
            SkillMatchService skillMatchService,
            AIRecommendationService aiRecommendationService) {
        this.skillExtractionService = skillExtractionService;
        this.skillMatchService = skillMatchService;
        this.aiRecommendationService = aiRecommendationService;
    }

    @Override
    public TailoredResume tailorResume(Resume resume, JobDescription jd, SkillMatchResult skillMatchResult) {

        // 1️⃣ AI skill extraction
        Set<String> resumeSkills = new LinkedHashSet<>(
                skillExtractionService.extractFromResume(
                        resume.getSummary()
                )
        );

        Set<String> jobSkills = new LinkedHashSet<>(
                skillExtractionService.extractFromJobDescription(
                        jd.getRawText()
                )
        );

        // 2️⃣ Skill matching
        SkillMatchResult matchResult = skillMatchService.matchSkills(
                        jobSkills,
                        resumeSkills,
                        new HashSet<>()
                );
        List<String> aiSuggestions =
                aiRecommendationService.suggestImprovements(
                        resume,
                        jd,
                        matchResult
                );

        // 3️⃣ Filter experience bullets by matched skills
        Set<String> relevantExperience =
                resume.getExperienceBullets().stream()
                        .filter(bullet ->
                                matchResult.getMatchedSkills().stream()
                                        .anyMatch(skill ->
                                                bullet.toLowerCase()
                                                      .contains(skill.toLowerCase())
                                        )
                        )
                        .collect(Collectors.toCollection(LinkedHashSet::new));

        // 4️⃣ Build tailored resume
        TailoredResume tailoredResume = new TailoredResume();
        tailoredResume.setTitle(resume.getTitle());
        tailoredResume.setSummary(resume.getSummary());
        tailoredResume.setMatchedSkills(new LinkedHashSet<>(matchResult.getMatchedSkills()));
        tailoredResume.setExperienceBullets(relevantExperience);
        tailoredResume.setMatchPercentage(matchResult.getMatchPercentage());
        tailoredResume.setAiSuggestions(aiSuggestions);

        double matchPercentage = (double) skillMatchResult.getMatchedSkills().size() /
                jd.getRequiredSkills().size() * 100.0;
        tailoredResume.setMatchPercentage(matchPercentage);
        return tailoredResume;
    }
}
