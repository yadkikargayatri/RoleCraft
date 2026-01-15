package com.rolecraft.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rolecraft.model.Resume;

@Component
public class ResumeParser {

    private final Tika tika = new Tika();

    public Resume parse(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {

            String content = tika.parseToString(is);

            Resume resume = new Resume();
            resume.setSummary(extractSummary(content));
            resume.setSkills(extractSkills(content));

            return resume;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse resume", e);
        }
    }

    private String extractSummary(String content) {
        String[] lines = content.split("\\r?\\n");
        return lines.length > 0 ? lines[0] : "";
    }

    private List<String> extractSkills(String content) {
        List<String> skills = new ArrayList<>();
        String lower = content.toLowerCase();

        if (lower.contains("java")) skills.add("Java");
        if (lower.contains("spring")) skills.add("Spring");
        if (lower.contains("aws")) skills.add("AWS");
        if (lower.contains("react")) skills.add("React");

        return skills;
    }
}
