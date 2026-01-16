package com.rolecraft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;
import com.rolecraft.service.ResumeTailorService;

@Service
public class ResumeTailorServiceImpl implements ResumeTailorService {

    @Override
    public TailoredResume tailorResume(Resume resume, JobDescription jd, SkillMatchResult matchResult) {
        TailoredResume tailored = new TailoredResume();

        // 1️⃣ Tailor summary
        String summary = "Experienced " + resume.getTitle() + " with strong skills in "
                + String.join(", ", matchResult.getMatchedSkills()) + ".";
        tailored.setSummary(summary);

        // 2️⃣ Tailor skills section
        List<String> tailoredSkills = new ArrayList<>(matchResult.getMatchedSkills());
        // Optional: add missing skills if desired
        tailored.setSkills(tailoredSkills);

        // 3️⃣ Tailor experience bullets
        List<String> bullets = new ArrayList<>();
        for (String bullet : resume.getExperienceBullets()) {
            for (String skill : matchResult.getMatchedSkills()) {
                if (bullet.toLowerCase().contains(skill.toLowerCase())) {
                    bullets.add("✔ " + bullet); // highlight matched skills
                }
            }
        }
        tailored.setExperienceBullets(bullets);

        return tailored;
    }
}
