package com.rolecraft.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rolecraft.ai.service.AIRecommendationService;
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
            AIRecommendationService aiRecommendationService
    ) {
        this.skillMatchService = skillMatchService;
        this.aiRecommendationService = aiRecommendationService;
    }

    @Override
    public TailoredResume tailorResume(Resume resume, JobDescription jd, SkillMatchResult skillMatchResult) {

        // -------------------------------
        // Null-safe handling of skills and keywords
        Set<String> requiredSkills = jd.getRequiredSkills() != null ? jd.getRequiredSkills() : new HashSet<>();
        Set<String> preferredSkills = jd.getPreferredSkills() != null ? jd.getPreferredSkills() : new HashSet<>();
        List<String> keywords = jd.getKeywords() != null ? jd.getKeywords() : new ArrayList<>();

        // Skill Matching
        skillMatchResult = skillMatchService.matchSkills(
                requiredSkills,
                resume.getSkills() != null ? resume.getSkills() : new HashSet<>(),
                new HashSet<>()
        );

        // Build TailoredResume
        TailoredResume tailoredResume = new TailoredResume();
        tailoredResume.setTitle(resume.getTitle());
        tailoredResume.setSummary(resume.getSummary());
        tailoredResume.setMatchedSkills(skillMatchResult.getMatchedSkills());
        tailoredResume.setExperienceBullets(resume.getExperienceBullets());

        // Match Percentage
        double matchPercentage = calculateMatchPercentage(skillMatchResult, requiredSkills, preferredSkills);
        tailoredResume.setMatchPercentage(matchPercentage);

        // ATS Score
        double atsScore = calculateATSScore(resume, requiredSkills, preferredSkills, keywords);
        tailoredResume.setAtsScore(atsScore);

        // AI Recommendations
        tailoredResume.setAiSuggestions(aiRecommendationService.suggestImprovements(resume, jd, skillMatchResult));

        return tailoredResume;
    }

    // -----------------------------
    private double calculateMatchPercentage(SkillMatchResult skillMatchResult,
                                            Set<String> requiredSkills,
                                            Set<String> preferredSkills) {
        int totalSkills = requiredSkills.size() + preferredSkills.size();
        if (totalSkills == 0) return 0.0;

        int matchedSkills = skillMatchResult.getMatchedSkills().size();
        return ((double) matchedSkills / totalSkills) * 100;
    }

    // -----------------------------
    private double calculateATSScore(Resume resume,
                                     Set<String> requiredSkills,
                                     Set<String> preferredSkills,
                                     List<String> keywords) {
        double requiredWeight = 0.5;
        double preferredWeight = 0.3;
        double experienceWeight = 0.2;

        // 1️⃣ Required Skills
        int matchedRequired = 0;
        for (String skill : requiredSkills) {
            if (resume.getSkills() != null && resume.getSkills().contains(skill)) matchedRequired++;
        }
        double requiredScore = requiredSkills.isEmpty() ? 0 : ((double) matchedRequired / requiredSkills.size()) * requiredWeight;

        // 2️⃣ Preferred Skills
        int matchedPreferred = 0;
        for (String skill : preferredSkills) {
            if (resume.getSkills() != null && resume.getSkills().contains(skill)) matchedPreferred++;
        }
        double preferredScore = preferredSkills.isEmpty() ? 0 : ((double) matchedPreferred / preferredSkills.size()) * preferredWeight;

        // 3️⃣ Experience alignment (keywords)
        int matchedExp = 0;
        for (String keyword : keywords) {
            if (resume.getSummary() != null && resume.getSummary().toLowerCase().contains(keyword.toLowerCase())) {
                matchedExp++;
            }
        }
        double experienceScore = keywords.isEmpty() ? 0 : ((double) matchedExp / keywords.size()) * experienceWeight;

        return (requiredScore + preferredScore + experienceScore) * 100;
    }
}
