package com.example.jobapi1.service;

import com.example.jobapi1.model.Job;
import com.example.jobapi1.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    public void saveJobs(List<Job> jobs)
    {
        jobRepository.saveAll(jobs);
    }
    private int jobidcount;
    private List<Job> jobList = new CopyOnWriteArrayList<>();

    public Job addjob(Job job){
        job.setId(jobidcount);
        jobList.add(job);
        jobidcount++;
        return job;
    }

    public List<Job> getJobList() {
        return jobRepository.findAll();
    }

    public Job getJob(int jobid)
    {

        return jobList.stream().filter(c -> c.getId() == jobid).findFirst().get();
    }

    public  Job updateJob(int jobid,Job job)
    {
        jobList.stream().forEach(c-> { if(c.getId() == jobid){c.setJob_title(job.getJob_title());
            c.setJob_location(job.getJob_location());
            c.setJob_type(job.getJob_type());}
        });
        return jobList.stream().filter(c->c.getId() == jobid).findFirst().get();
    }
}
