package com.example.Jobportal.Repository;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerRepository extends JpaRepository<JobSeekerProfile,Integer>{
    JobSeekerProfile findByUserId(int userId);
}
