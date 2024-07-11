package com.example.Jobportal.Repository;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {
    List<Job> findByEmployerProfileId(int empId);

}
