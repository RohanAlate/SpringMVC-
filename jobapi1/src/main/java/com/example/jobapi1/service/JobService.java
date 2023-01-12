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
        int size = jobs.size();
        int counter =0;
        List<Job> temp = new ArrayList<>();
        for(Job jb : jobs){
            temp.add(jb);

            if((counter+1)%500==0 || (counter+1)==size){
                jobRepository.saveAll(temp);
                temp.clear();
            }
            counter++;
        }
    }
    private int jobidcount = 1;
    private List<Job> jobList = new CopyOnWriteArrayList<>();

    public Job addjob(Job job){
        job.setId(jobidcount);
        jobList.add(job);
        jobidcount++;
        return job;
    }

    public List<Job> getJobList() {
        return jobList;
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
