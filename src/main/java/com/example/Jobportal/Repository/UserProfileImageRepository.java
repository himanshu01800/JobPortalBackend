package com.example.Jobportal.Repository;

import com.example.Jobportal.Model.UserProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileImageRepository extends JpaRepository<UserProfileImage, Integer> {
    UserProfileImage findByUserId(int userId);
}
