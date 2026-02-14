package com.rolecraft.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
@Entity
@Table(name = "job_descriptions")
public class JobDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title must not be blank")
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "jd_required_skills", joinColumns = @JoinColumn(name = "jd_id"))
    @Column(name = "skill")
    @NotEmpty(message = "Required skills must not be blank")
    private Set<String> requiredSkills = new LinkedHashSet<>(); // was incorrectly List<String>

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "jd_preferred_skills", joinColumns = @JoinColumn(name = "jd_id"))
    @Column(name = "skill")
    private Set<String> preferredSkills = new LinkedHashSet<>(); // was incorrectly List<String>
    
    private List<String> responsibilities;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "jd_keywords", joinColumns = @JoinColumn(name = "jd_id"))
    @Column(name = "keyword")
    private List<String> keywords = new ArrayList<>(); // initialized to avoid null pointer

    private String rawText;
    private List<String> skills = new ArrayList<>(); // Added field to hold extracted skills

    @OneToMany(mappedBy = "jobDescription", cascade = CascadeType.ALL)
    //private List<TailoredResume> tailoredResumes;

    public Long getId() {
        return id;
    }
   
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills2) {
        this.skills = new ArrayList<>(skills2);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> extractedSkills) {
        this.requiredSkills = new LinkedHashSet<>(extractedSkills);
    }

    public Set<String> getPreferredSkills() {
        return preferredSkills;
    }

    public void setPreferredSkills(Set<String> extractedSkills) {
        this.preferredSkills = new LinkedHashSet<>(extractedSkills);
    }

    public List<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
