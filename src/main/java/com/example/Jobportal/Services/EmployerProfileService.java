package com.example.Jobportal.Services;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.User;
import com.example.Jobportal.Repository.EmployerRepository;
import com.example.Jobportal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EmployerProfileService {
    @Autowired
    private  EmployerRepository employerProfileRepository;
    @Autowired
    private  UserRepository userRepository;



    public EmployerProfile createOrUpdateEmployerProfile(int userId, EmployerProfile employerProfile) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }


        EmployerProfile existingProfile = employerProfileRepository.findByUserId(userId);

        if (existingProfile != null) {
            existingProfile.setWebSiteUrl(employerProfile.getWebSiteUrl());
            existingProfile.setAddress(employerProfile.getAddress());
            existingProfile.setDescription(employerProfile.getDescription());
            existingProfile.setEstablished(employerProfile.getEstablished());
            // Handle jobs if necessary
            return employerProfileRepository.save(existingProfile);
        } else {
            employerProfile.setUser(user);
            return employerProfileRepository.save(employerProfile);
        }
    }

    public EmployerProfile getEmployerProfile(int userId){
        EmployerProfile employerProfile=employerProfileRepository.findByUserId(userId);
        return  employerProfile;
    }

}