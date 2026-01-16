package com.rolecraft.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rolecraft.model.Resume;

@Component
public class ResumeParser {

    private final Tika tika = new Tika();

    // Define common skills for keyword matching
    private final List<String> SKILL_KEYWORDS = Arrays.asList(
            "Java", "Spring", "Spring Boot", "AWS", "React", "Vue", "SQL",
            "Python", "Docker", "Kubernetes", "Go", "Microservices", "REST",
            "PostgreSQL", "Hibernate", "TypeScript", "JavaScript"
    );

    public Resume parse(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            // Extract raw text from PDF/DOCX
            String content = tika.parseToString(is);
            // Normalize spaces
            content = content.replaceAll("\\r?\\n", " ");

            Resume resume = new Resume();
            resume.setSummary(extractSummary(content));
            resume.setSkills(extractSkills(content));

            return resume;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse resume", e);
        }
    }

    /**
     * Extract the summary section from the resume
     */
    private String extractSummary(String content) {
        String summary = "";

        // Regex: look for SUMMARY or Professional Summary heading
        Pattern pattern = Pattern.compile(
                "(?i)(SUMMARY|Professional Summary)\\s*[:\\-]?\\s*(.*?)(?=\\s+[A-Z]{2,})"
        );
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            summary = matcher.group(2).trim();
        }

        return summary;
    }

    /**
     * Extract skills from the resume
     */
    private List<String> extractSkills(String content) {
        Set<String> foundSkills = new LinkedHashSet<>();
        String lowerContent = content.toLowerCase();

        for (String skill : SKILL_KEYWORDS) {
            if (lowerContent.contains(skill.toLowerCase())) {
                foundSkills.add(skill);
            }
        }

        return new ArrayList<>(foundSkills);
    }
}
