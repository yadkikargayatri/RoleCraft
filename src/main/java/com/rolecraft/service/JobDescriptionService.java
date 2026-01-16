package com.rolecraft.service;

import com.rolecraft.model.JobDescription;

public interface JobDescriptionService {
    JobDescription parseDescription(String jdText);
}
