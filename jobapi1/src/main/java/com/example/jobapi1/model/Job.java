package com.example.jobapi1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="jobs")
public class Job {
    @Id
    private int id;
    @Column(name="Job_title")
    private String job_title;
    @Column(name="Job_type")
    private String job_type;
    @Column(name="Job_location")
    private  String job_location;
}