package com.rolecraft.service;

import java.util.Set;

import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;

public interface SkillMatchService {

    SkillMatchResult match(Set<String> requiredSkills, Resume resume);
}
