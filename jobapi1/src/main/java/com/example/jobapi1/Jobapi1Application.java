package com.example.jobapi1;

import com.example.jobapi1.service.JobService;
import com.example.jobapi1.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jobapi1Application {

	public static void main(String[] args) {

		SpringApplication.run(Jobapi1Application.class, args);
	}
    @Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobService jobService;
}
