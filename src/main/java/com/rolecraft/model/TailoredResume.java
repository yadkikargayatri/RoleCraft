package com.rolecraft.model;
import java.util.List;


public class TailoredResume {
    public String summary;
    public List<String> skills;
    public List<String> experienceBullets;

    public String getSummary() { return summary; }
    public void setSummary(String summary) {this.summary = summary; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) {this.skills = skills; }

    public List<String> getExperienceBullets() { return experienceBullets; }
    public void setExperienceBullets(List<String> experienceBullets) {this.experienceBullets = experienceBullets; }
}
