package com.rolecraft.model;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "resumes")

public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Resume title cannot be empty")
    private String title;

    @Column(length = 3000)
    @NotBlank(message = "Resume summary cannot be empty")
    @Size(max = 500, message = "Resume summary must not exceed 500 characters")
    private String summary;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "resume_skills", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "skill")
    @NotEmpty(message = "Resume skills must not be empty")
    private Set<String> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "resume_experience", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(name = "bullet")
    @NotEmpty(message = "Resume experience bullets must not be empty")
    private Set<@NotBlank String> experienceBullets;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Set<String> getSkills() {
        return skills;
    }
    
    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public String getTitle() {
       return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getExperienceBullets() {
        return experienceBullets;
    }
    public void setExperienceBullets(Set<String> experienceBullets) {
        this.experienceBullets = experienceBullets;
    }   
}
