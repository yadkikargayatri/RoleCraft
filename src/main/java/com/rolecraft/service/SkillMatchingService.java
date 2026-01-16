package com.rolecraft.service;
import java.util.List;

import com.rolecraft.model.SkillMatchResult;

public interface SkillMatchingService {

    SkillMatchResult matchSkills(List<String> resumeSkills, List<String> jobSkills);
    
}