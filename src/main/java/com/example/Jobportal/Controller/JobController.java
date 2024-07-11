package com.example.Jobportal.Controller;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.Job;
import com.example.Jobportal.Model.User;
import com.example.Jobportal.Repository.JobRepository;
import com.example.Jobportal.Repository.UserRepository;
import com.example.Jobportal.Services.JobServices;
import  java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class JobController {

    @Autowired
    private JobServices jobServices;

    @PostMapping("company/{empId}")
    public ResponseEntity<Job> addJob(@PathVariable int empId,@RequestBody Job job) {
        Job updateJob = jobServices.createJob(empId, job);
        return ResponseEntity.ok(updateJob);
    }

    @GetMapping("jobseeker/{jobSeekerProfileId}/{jobId}")
    public ResponseEntity<String> applyForJob(@PathVariable int jobSeekerProfileId, @PathVariable int jobId) {
        try {
            jobServices.applyForJob(jobSeekerProfileId, jobId);
            return ResponseEntity.ok("Application successful");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs() {
        try {
            List<Job> jobs = jobServices.getAllJobs();
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("company/find/posted/{empId}")
    public ResponseEntity<List<Job>> findPostedJob(@PathVariable int empId) {
        try {
            List<Job> jobs = jobServices.getPostedJobs(empId);
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("company/remove/{jobId}")
    public String deleteJob(@PathVariable int jobId){
        return jobServices.removeJob(jobId);

    }
}
