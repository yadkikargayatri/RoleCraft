package com.rolecraft.service;

import java.util.List;
import java.util.Set;

import com.rolecraft.model.Resume;

public interface ATSScoringService {
   
    double calculateATSScore(
            Set<String> resumeSkills,
            List<String> keywords,
            Set<String> requiredSkills,
            Set<String> preferredSkills,
            Resume resume);
}
