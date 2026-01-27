package com.rolecraft.service.impl;

import java.util.Set;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.SkillMatchResult;
import com.rolecraft.model.TailoredResume;

public class ResumeTailorManualTest {
    public static void main(String[] args) {
        ResumeTailorServiceImpl service = new ResumeTailorServiceImpl();

        Resume resume = new Resume();
        resume.setExperienceBullets(Set.of(
            "Developed REST APIs using Spring Boot",
            "Worked with AWS S3 for file storage",
            "Implemented CI/CD pipelines"
        ));
        resume.setSkills(Set.of("Java", "Spring Boot", "AWS", "CI/CD"));

        JobDescription jd = new JobDescription();
        jd.setRequiredSkills(Set.of("Java", "Spring Boot", "AWS"));

        SkillMatchResult matchResult = new SkillMatchResult();

        TailoredResume tailoredResume = service.tailorResume(resume, jd, matchResult);
        //String tailoredResume = service.tailorResume(resume, jd, matchResult);
        System.out.println("Tailored Resume:\n" + tailoredResume);
    }
}
