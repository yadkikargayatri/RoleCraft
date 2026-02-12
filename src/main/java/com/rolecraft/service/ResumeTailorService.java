package com.rolecraft.service;

import com.rolecraft.model.JobDescription;
import com.rolecraft.model.Resume;
import com.rolecraft.model.TailoredResume;

public interface ResumeTailorService {
     
     TailoredResume tailorResume(Resume resume, JobDescription jd);
     

}
