package com.example.Jobportal.Services;

import com.example.Jobportal.Model.User;
import com.example.Jobportal.Model.UserProfileImage;
import com.example.Jobportal.Repository.UserProfileImageRepository;
import com.example.Jobportal.Repository.UserRepository;
import com.example.Jobportal.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileImageRepository userProfileImageRepository;
    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findAllUsersExcept(String email) {
        return userRepository.findAll().stream()
                .filter(user -> !user.getUsername().equals(email))
                .toList();
    }


    public void saveProfileImage(int userId, MultipartFile file) throws IOException {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        UserProfileImage userProfileImage = userProfileImageRepository.findByUserId(userId);
        if (userProfileImage == null) {
            userProfileImage = new UserProfileImage();
            userProfileImage.setUserId(userId);
        }

        userProfileImage.setProfileImage(file.getBytes());
        userProfileImageRepository.save(userProfileImage);
    }

    public UserProfileImage getProfileImage(int userId) {
        return userProfileImageRepository.findByUserId(userId);
    }

    public List<User> allUser(){
        return userRepository.findAll();
    }

    public User AddUser( User user){
        userRepository.save(user);
        return user;
    }
    public User findUser(String userid,String password){
      User u=  userRepository.findByUseridAndPassword(userid,password);
      return u;
    }
    public String changePassword(int id ,String password){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(password);
            userRepository.save(user);
            return "password changed";
            // Save the updated user object back to the repository
        } else {
            // Handle the case where the user with the given id is not found
            return "something went wrong";
        }


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);

    }

    public List<User> getJobSeekers() {
        return userRepository.findByRole("jobseeker");
    }
    public List<User> getEmployers() {
        return userRepository.findByRole("employer");
    }
}


