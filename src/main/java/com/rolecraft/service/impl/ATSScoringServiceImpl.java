package com.rolecraft.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rolecraft.model.Resume;
import com.rolecraft.service.ATSScoringService;

@Service
public class ATSScoringServiceImpl implements ATSScoringService {

    @Override
    public double calculateATSScore(
            Set<String> resumeSkills,
            List<String> keywords,
            Set<String> requiredSkills,
            Set<String> preferredSkills,
            Resume resume) {

        double requiredWeight = 0.5;
        double preferredWeight = 0.3;
        double experienceWeight = 0.2;

        long matchedRequired = requiredSkills.stream()
                .filter(resumeSkills::contains)
                .count();

        double requiredScore = requiredSkills.isEmpty()
                ? 0
                : ((double) matchedRequired / requiredSkills.size()) * requiredWeight;

        long matchedPreferred = preferredSkills.stream()
                .filter(resumeSkills::contains)
                .count();

        double preferredScore = preferredSkills.isEmpty()
                ? 0
                : ((double) matchedPreferred / preferredSkills.size()) * preferredWeight;

        String summary = resume.getSummary() == null ? "" : resume.getSummary().toLowerCase();

        long matchedKeywords = keywords.stream()
                .filter(k -> summary.contains(k.toLowerCase()))
                .count();

        double experienceScore = keywords.isEmpty()
                ? 0
                : ((double) matchedKeywords / keywords.size()) * experienceWeight;

        return (requiredScore + preferredScore + experienceScore) * 100;
    }
}
