package com.example.Jobportal.Repository;

import com.example.Jobportal.Model.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminProfile,Integer> {
}
