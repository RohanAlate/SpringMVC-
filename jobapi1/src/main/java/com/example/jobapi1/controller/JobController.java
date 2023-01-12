package com.example.jobapi1.controller;

import com.example.jobapi1.exception.ResourceNotFoundException;
import com.example.jobapi1.service.JobService;
import com.example.jobapi1.repository.JobRepository;
import com.example.jobapi1.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v3/Jobs")
public class JobController {
        @Autowired
        private JobService jobService;
        @Autowired
        private JobRepository jobRepository;
        @PostMapping
        public Job addJob(@RequestBody Job job) {
            return jobRepository.save(job);
        }
    @PostMapping("/save")
    public ResponseEntity<Void> saveEmployees(@RequestBody List<Job> jobs){
        jobService.saveJobs(jobs);
        System.out.println("Data saved");
        return new ResponseEntity<>(HttpStatus.OK);

    }
        @GetMapping
        public List<Job> getAllJobs()
        {
            return jobRepository.findAll();
        }

        @GetMapping(value = "/{jobid}")
        public Job getJob(@PathVariable("jobid") int jobid) {
            return jobService.getJob(jobid);
        }
    @PutMapping("{id}")
    public ResponseEntity<Job> updateJob(@PathVariable int id,@RequestBody Job JobDetails) {
        Job updateJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not exist with id: " + id));

        updateJob.setJob_title(JobDetails.getJob_title());
        updateJob.setJob_type(JobDetails.getJob_type());
        updateJob.setJob_location(JobDetails.getJob_location());

        jobRepository.save(updateJob);

        return ResponseEntity.ok(updateJob);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable int id){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not exist with id: " + id));

        jobRepository.delete(job);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}