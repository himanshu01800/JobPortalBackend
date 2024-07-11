package com.example.Jobportal.Controller;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.JobSeekerProfile;
import com.example.Jobportal.Services.EmployerProfileService;
import com.example.Jobportal.Services.JobSeekerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileController {
    @ Autowired
    private  EmployerProfileService employerProfileService;
    @Autowired
    private JobSeekerProfileService jobSeekerProfileService;



    @PostMapping("company/profile/{userId}")
    public ResponseEntity<EmployerProfile> createOrUpdateEmployerProfile(
            @PathVariable int userId,
            @RequestBody EmployerProfile employerProfile) {
        EmployerProfile updatedProfile = employerProfileService.createOrUpdateEmployerProfile(userId, employerProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @PostMapping("jobseeker/profile/{userId}")
    public ResponseEntity<JobSeekerProfile> createOrUpdatejobSeekerProfile(
            @PathVariable int userId,
            @RequestBody JobSeekerProfile jobSeekerProfile) {
        JobSeekerProfile updatedProfile = jobSeekerProfileService.CreateOrUpdateJobSeeker(userId,jobSeekerProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @GetMapping("company/profile/{userId}")
    public ResponseEntity<EmployerProfile> getEmployerProfile(@PathVariable int userId){
        EmployerProfile employerProfile=employerProfileService.getEmployerProfile(userId);
        return ResponseEntity.ok(employerProfile);
    }

    @GetMapping("jobseeker/profile/{userId}")
    public ResponseEntity<JobSeekerProfile> getJobSeekerProfile(@PathVariable int userId){
        JobSeekerProfile jobSeekerProfile=jobSeekerProfileService.getJobSeekerProfile(userId);
        return ResponseEntity.ok(jobSeekerProfile);
    }
}
