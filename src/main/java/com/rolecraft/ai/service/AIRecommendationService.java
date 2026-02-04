package com.rolecraft.ai.service;

import java.util.List;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

public interface AIRecommendationService {  // suggests improvemements to the resume based on jd and skill match result

    List<String> suggestImprovements(Resume resume, JobDescription jd, SkillMatchResult skillMatchResult);
    
}
