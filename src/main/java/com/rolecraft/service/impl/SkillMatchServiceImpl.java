package com.rolecraft.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.service.SkillMatchService;

@Service
public class SkillMatchServiceImpl implements SkillMatchService {

    @Override
    public SkillMatchResult match(Set<String> requiredSkills, Resume resume) {

        Set<String> resumeSkills = normalize(resume.getSkills());
        Set<String> jobSkills = normalize(requiredSkills);

        List<String> matched = new ArrayList<>();
        List<String> missing = new ArrayList<>();

        for (String skill : jobSkills) {
            if (resumeSkills.contains(skill)) {
                matched.add(skill);
            } else {
                missing.add(skill);
            }
        }

        double matchPercentage =
                jobSkills.isEmpty()
                        ? 0
                        : ((double) matched.size() / jobSkills.size()) * 100;

        SkillMatchResult result = new SkillMatchResult();
        result.setMatchedSkills(matched);
        result.setMissingSkills(missing);
        result.setMatchPercentage(
                Math.round(matchPercentage * 100.0) / 100.0
        );

        return result;
    }

    private Set<String> normalize(Collection<String> skills) {
        Set<String> normalized = new HashSet<>();
        if (skills == null) return normalized;

        for (String skill : skills) {
            normalized.add(skill.toLowerCase().trim());
        }
        return normalized;
    }
}
