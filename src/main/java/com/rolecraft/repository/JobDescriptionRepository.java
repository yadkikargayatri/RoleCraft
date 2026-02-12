package com.rolecraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rolecraft.model.JobDescription;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {

}
