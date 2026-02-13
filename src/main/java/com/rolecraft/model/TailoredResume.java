package com.rolecraft.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tailored_resumes")
public class TailoredResume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String summary;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tailored_matched_skills", joinColumns = @JoinColumn(name = "tailored_id"))
    @Column(name = "skill")
    private Set<String> matchedSkills = new LinkedHashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tailored_experience", joinColumns = @JoinColumn(name = "tailored_id"))
    @Column(name = "bullet")
    private Set<String> experienceBullets = new LinkedHashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tailored_ai_suggestions", joinColumns = @JoinColumn(name = "tailored_id"))
    @Column(name = "suggestion")
    private List<String> aiSuggestions = new ArrayList<>();
    
    private double matchPercentage;
    private double atsScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_description_id")
    @JsonIgnore
    private JobDescription jobDescription;

    public TailoredResume() {}

    // ---------- Getters & Setters ----------
    
    public Long getId() {
        return id;
    }
    // public void setId(Long id) {
    //     this.id = id;
    // }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(Set<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public Set<String> getExperienceBullets() {
        return experienceBullets;
    }

    public void setExperienceBullets(Set<String> experienceBullets) {
        this.experienceBullets = experienceBullets;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
    public List<String> getAiSuggestions() {
        return aiSuggestions;
    }
    public void setAiSuggestions(List<String> aiSuggestions) {
        this.aiSuggestions = aiSuggestions;
    }

    public double getAtsScore() {
        return atsScore;
    }
    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }
    public Resume getResume() {
        return resume;
    }   
    public void setResume(Resume resume) {
        this.resume = resume;
    }
    public JobDescription getJobDescription() {
        return jobDescription;
    }
    public void setJobDescription(JobDescription jobDescription) {
        this.jobDescription = jobDescription;
    }
}
