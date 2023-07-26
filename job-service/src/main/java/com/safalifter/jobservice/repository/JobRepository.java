package com.safalifter.jobservice.repository;

import com.safalifter.jobservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> getJobsByCategory_Id(String id);

    List<Job> getJobsByKeysContainsIgnoreCase(String key);
}
