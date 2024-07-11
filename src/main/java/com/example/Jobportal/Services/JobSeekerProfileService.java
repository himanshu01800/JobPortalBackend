package com.example.Jobportal.Services;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.JobSeekerProfile;
import com.example.Jobportal.Model.User;
import com.example.Jobportal.Repository.AdminRepository;
import com.example.Jobportal.Repository.JobSeekerRepository;
import com.example.Jobportal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class JobSeekerProfileService {
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;


    public JobSeekerProfile CreateOrUpdateJobSeeker(int userId,JobSeekerProfile jobSeekerProfile){
    Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
        throw new RuntimeException("User not found");
    }
        User user = optionalUser.get();


    JobSeekerProfile existingProfile = jobSeekerRepository.findByUserId(userId);

        if (existingProfile != null) {
         existingProfile.setSkills(jobSeekerProfile.getSkills());
         existingProfile.setResume(jobSeekerProfile.getResume());
        // Handle jobs if necessary
        return jobSeekerRepository.save(existingProfile);
    } else {
        jobSeekerProfile.setUser(user);
        return jobSeekerRepository.save(jobSeekerProfile);
    }
}
    public JobSeekerProfile getJobSeekerProfile(int userId){
        JobSeekerProfile jobSeekerProfile=jobSeekerRepository.findByUserId(userId);
        return  jobSeekerProfile;
    }
}
