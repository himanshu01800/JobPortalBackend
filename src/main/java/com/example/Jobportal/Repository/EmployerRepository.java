package com.example.Jobportal.Repository;

import com.example.Jobportal.Model.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<EmployerProfile,Integer> {
    EmployerProfile findByUserId(int userId);

}
