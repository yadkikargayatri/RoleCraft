package com.rolecraft.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rolecraft.ai.service.AIRecommendationService;
import com.rolecraft.exception.InvalidJobDescriptionException;
import com.rolecraft.exception.InvalidResumeException;
import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;
import com.rolecraft.service.SkillMatchService;

@Service
public class ResumeTailorServiceImpl implements ResumeTailorService {

    private final SkillMatchService skillMatchService;
    private final AIRecommendationService aiRecommendationService;

    public ResumeTailorServiceImpl(
            SkillMatchService skillMatchService,
            AIRecommendationService aiRecommendationService) {
        this.skillMatchService = skillMatchService;
        this.aiRecommendationService = aiRecommendationService;
    }

    // =============================
    // PUBLIC API
    // =============================
    @Override
    public TailoredResume tailorResume(Resume resume, JobDescription jd) {

        if (resume == null || jd == null) {
            throw new IllegalArgumentException("Resume and JobDescription must not be null");
        }

        Set<String> resumeSkills = safeSet(resume.getSkills());
        Set<String> requiredSkills = safeSet(jd.getRequiredSkills());
        Set<String> preferredSkills = safeSet(jd.getPreferredSkills());
        List<String> keywords = safeList(jd.getKeywords());

        validateResume(resume);
        validateJobDescription(jd);

        // Skill matching
        SkillMatchResult skillMatchResult = skillMatchService.matchSkills(
                requiredSkills,
                resumeSkills,
                new HashSet<>()
        );

        // Build tailored resume
        TailoredResume tailoredResume = new TailoredResume();
        tailoredResume.setTitle(safeString(resume.getTitle()));
        tailoredResume.setSummary(safeString(resume.getSummary()));
        tailoredResume.setMatchedSkills(skillMatchResult.getMatchedSkills());
        tailoredResume.setExperienceBullets(safeSet(resume.getExperienceBullets()));

        // Scores
        tailoredResume.setMatchPercentage(
                calculateMatchPercentage(skillMatchResult, requiredSkills, preferredSkills)
        );

        tailoredResume.setAtsScore(
                calculateATSScore(resumeSkills, keywords, requiredSkills, preferredSkills, resume)
        );

        // AI suggestions
        tailoredResume.setAiSuggestions(
                aiRecommendationService.suggestImprovements(resume, jd, skillMatchResult)
        );

        return tailoredResume;
    }

    // =============================
    // SCORE CALCULATIONS
    // =============================
    private double calculateMatchPercentage(
            SkillMatchResult result,
            Set<String> required,
            Set<String> preferred) {

        int total = required.size() + preferred.size();
        if (total == 0) return 0.0;

        return ((double) result.getMatchedSkills().size() / total) * 100;
    }

    private double calculateATSScore(
            Set<String> resumeSkills,
            List<String> keywords,
            Set<String> requiredSkills,
            Set<String> preferredSkills,
            Resume resume) {

        double requiredWeight = 0.5;
        double preferredWeight = 0.3;
        double experienceWeight = 0.2;

        // Required skills
        long matchedRequired = requiredSkills.stream()
                .filter(resumeSkills::contains)
                .count();

        double requiredScore = requiredSkills.isEmpty()
                ? 0
                : ((double) matchedRequired / requiredSkills.size()) * requiredWeight;

        // Preferred skills
        long matchedPreferred = preferredSkills.stream()
                .filter(resumeSkills::contains)
                .count();

        double preferredScore = preferredSkills.isEmpty()
                ? 0
                : ((double) matchedPreferred / preferredSkills.size()) * preferredWeight;

        // Experience keywords
        String summary = safeString(resume.getSummary()).toLowerCase();
        long matchedKeywords = keywords.stream()
                .filter(k -> summary.contains(k.toLowerCase()))
                .count();

        double experienceScore = keywords.isEmpty()
                ? 0
                : ((double) matchedKeywords / keywords.size()) * experienceWeight;

        return (requiredScore + preferredScore + experienceScore) * 100;
    }

    // =============================
    // SAFE UTILITIES
    // =============================
    private Set<String> safeSet(Set<String> input) {
        return input == null ? Set.of() : input;
    }

    private List<String> safeList(List<String> input) {
        return input == null ? List.of() : input;
    }

    private String safeString(String input) {
        return input == null ? "" : input;
    }

    // =============================
    // VALIDATION
    // =============================
    private void validateResume(Resume resume) {
        if (resume == null) {
            throw new InvalidResumeException("Resume must not be null");
        }
        if (resume.getTitle() == null || resume.getTitle().isBlank()) {
            throw new InvalidResumeException("Resume title is required");
        }
        if (resume.getSummary() == null || resume.getSummary().isBlank()) {
            throw new InvalidResumeException("Resume summary is required");
        }
        if (resume.getSkills() == null || resume.getSkills().isEmpty()) {
            throw new InvalidResumeException("Resume must contain at least one skill");
        }
    }

    private void validateJobDescription(JobDescription jd) {
        if (jd == null) {
            throw new InvalidJobDescriptionException("Job description must not be null");
        }
        if (jd.getTitle() == null || jd.getTitle().isBlank()) {
            throw new InvalidJobDescriptionException("Job title is required");
        }
        if (jd.getRequiredSkills() == null || jd.getRequiredSkills().isEmpty()) {
            throw new InvalidJobDescriptionException("Job description must contain required skills");
        }
    }
}
