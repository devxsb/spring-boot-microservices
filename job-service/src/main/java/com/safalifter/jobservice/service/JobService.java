package com.safalifter.jobservice.service;

import com.safalifter.jobservice.exc.NotFoundException;
import com.safalifter.jobservice.model.Category;
import com.safalifter.jobservice.model.Job;
import com.safalifter.jobservice.repository.JobRepository;
import com.safalifter.jobservice.request.job.JobCreateRequest;
import com.safalifter.jobservice.request.job.JobUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final CategoryService categoryService;

    public Job createJob(JobCreateRequest request) {
        Category category = categoryService.getCategoryById(request.getCategoryId());
        return jobRepository.save(Job.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(category)
                .keys(List.of(request.getKeys()))
                .imagesId(List.of(request.getImagesId()))
                .build());
    }

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public Job getJobById(String id) {
        return findJobById(id);
    }

    public Job updateJob(JobUpdateRequest request) {
        Job toUpdate = findJobById(request.getCategoryId());
        toUpdate.setName(Optional.ofNullable(request.getName()).orElse(request.getName()));
        toUpdate.setDescription(Optional.ofNullable(request.getDescription()).orElse(request.getDescription()));
        toUpdate.setCategory(Optional.of(request.getCategoryId())
                .map(categoryService::getCategoryById).orElse(toUpdate.getCategory()));
        toUpdate.setKeys(Optional.of(List.of(request.getKeys())).orElse(toUpdate.getKeys()));
        toUpdate.setImagesId(Optional.of(List.of(request.getImagesId())).orElse(toUpdate.getImagesId()));
        return jobRepository.save(toUpdate);
    }

    public void deleteJobById(String id) {
        jobRepository.deleteById(id);
    }

    public List<Job> getJobsByCategoryId(String id) {
        return jobRepository.getJobsByCategoryId(id);
    }

    public List<Job> getJobsThatFitYourNeeds(String needs) {
        String[] keys = needs.replaceAll("\"", "").split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        Arrays.stream(keys).forEach(key -> {
            jobRepository.getJobsByKeysContainsIgnoreCase(key).forEach(job -> {
                if (map.containsKey(job.getId())) {
                    int count = map.get(job.getId());
                    map.put(job.getId(), count + 1);
                } else map.put(job.getId(), 1);
            });
        });
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> findJobById(entry.getKey()))
                .collect(Collectors.toList());
    }

    protected Job findJobById(String id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found"));
    }
}
