package com.rolecraft.ai.service;

import java.util.List;

public interface SkillExtractionService {

    List<String> extractFromResume(String resumeText);  // extracts skills from resume text

    List<String> extractFromJobDescription(String jobDescriptionText);   // extracts skills from a job description text
}
