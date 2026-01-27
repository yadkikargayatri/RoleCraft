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

    private final List<String> SKILL_KEYWORDS = Arrays.asList(
            "Java", "Spring", "Spring Boot", "AWS", "React", "Vue", "SQL",
            "Python", "Docker", "Kubernetes", "Go", "Microservices", "REST",
            "PostgreSQL", "Hibernate", "TypeScript", "JavaScript"
    );

    public Resume parse(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {

            String content = tika.parseToString(is);
            content = content.replaceAll("\\r?\\n+", "\n");

            Resume resume = new Resume();
            resume.setTitle(extractTitle(content));
            resume.setSummary(extractSummary(content));

            // ✅ Convert List → Set (NO CASTING)
            resume.setSkills(new LinkedHashSet<>(extractSkills(content)));
            resume.setExperienceBullets(new LinkedHashSet<>(extractExperience(content)));

            return resume;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse resume", e);
        }
    }

    public List<String> extractExperience(String text) {
        // Stub for now (safe)
        return List.of();
    }

    private String extractTitle(String text) {
        if (text == null || text.isEmpty()) {
            return "Software Engineer";
        }

        String[] lines = text.split("\\R");

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.matches("^[A-Z ]{5,40}$") && trimmed.contains("ENGINEER")) {
                return toTitleCase(trimmed);
            }

            if (trimmed.matches("(?i).*(software engineer|backend engineer|java developer).*")) {
                return capitalizeWords(trimmed);
            }
        }

        return "Software Engineer";
    }

    private String toTitleCase(String input) {
        return capitalizeWords(input.toLowerCase());
    }

    private String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) continue;
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1))
              .append(" ");
        }
        return sb.toString().trim();
    }

    private String extractSummary(String content) {
        Pattern pattern = Pattern.compile(
                "(?i)(SUMMARY|Professional Summary)\\s*[:\\-]?\\s*(.*?)(?=\\s+[A-Z]{2,})"
        );
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(2).trim();
        }

        return "";
    }

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
