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

    int matched = matchResult.getMatchedSkills().size();
    int required = jd.getRequiredSkills().size();

    double matchPercentage = required == 0
        ? 0.0
        : Math.round(((double) matched / required) * 10000.0) / 100.0;

    tailored.setMatchPercentage(matchPercentage);

    // 1️⃣ Tailor summary
    String summary = "Experienced " + resume.getTitle() + " with strong skills in "
            + String.join(", ", matchResult.getMatchedSkills()) + ".";
    tailored.setSummary(summary);

    // 2️⃣ Tailor skills section
    List<String> tailoredSkills = matchResult.getMatchedSkills() != null ? new ArrayList<>(matchResult.getMatchedSkills()) : new ArrayList<>();
    tailored.setSkills(tailoredSkills);

    // 3️⃣ Tailor experience bullets
    List<String> bullets = new ArrayList<>();
    if (resume.getExperienceBullets() != null) {
        for (String bullet : resume.getExperienceBullets()) {
            boolean matches = matchResult.getMatchedSkills().stream()
                .anyMatch(skill -> bullet.toLowerCase().contains(skill.toLowerCase()));

            if (matches) {
                bullets.add("- " + bullet);
            }
        }
    }

    tailored.setExperienceBullets(bullets);

    System.out.println("Matched: " + matched);
System.out.println("Required: " + required);


if (matchPercentage < 50) {
    throw new IllegalArgumentException("Insufficient skill match");
}



    // 4️⃣ Match percentage
    //tailored.setMatchPercentage(matchResult.getMatchPercentage());

    return tailored;
}

}
