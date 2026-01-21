package com.rolecraft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rolecraft.model.JobDescription;
import com.rolecraft.service.JobDescriptionService;

@Service
public class JobDescriptionServiceImpl implements JobDescriptionService {

    @Override
    public JobDescription parseDescription(String jobDescription) {

        JobDescription jd = new JobDescription();
        jd.setRawText(jobDescription);
        List<String> skills = extractSkills(jobDescription);
        jd.setSkills(skills);

        return jd;
    }

    private List<String> extractSkills(String text) {
        List<String> skills = new ArrayList<>();

        String lowerText = text.toLowerCase();

        if (lowerText.contains("java")) skills.add("Java");
        if (lowerText.contains("spring")) skills.add("Spring");
        if (lowerText.contains("aws")) skills.add("AWS");
        if (lowerText.contains("react")) skills.add("React");
        if (lowerText.contains("kubernetes")) skills.add("Kubernetes");

        return skills;
    }
}
