package com.example.Jobportal.Services;

import com.example.Jobportal.Model.EmployerProfile;
import com.example.Jobportal.Model.Job;
import com.example.Jobportal.Model.JobSeekerProfile;
import com.example.Jobportal.Model.User;
import com.example.Jobportal.Repository.EmployerRepository;
import com.example.Jobportal.Repository.JobRepository;
import com.example.Jobportal.Repository.JobSeekerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServices {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    public Job createJob(int empId,Job job) {
        Optional<EmployerProfile> optionalEmployerProfile=employerRepository.findById(empId);
        EmployerProfile emp=optionalEmployerProfile.get();
        job.setEmployerProfile(emp);

       Job jb=jobRepository.save(job);
       System.out.println(jb);
       return jb;



    }

    @Transactional
    public void applyForJob(int jobSeekerProfileId, int jobId) {
        Optional<JobSeekerProfile> jobSeekerProfileOpt = jobSeekerRepository.findById(jobSeekerProfileId);
        Optional<Job> jobOpt = jobRepository.findById(jobId);

        if (jobSeekerProfileOpt.isPresent() && jobOpt.isPresent()) {
            JobSeekerProfile jobSeekerProfile = jobSeekerProfileOpt.get();
            Job job = jobOpt.get();

            jobSeekerProfile.getAppliedJobs().add(job);


            job.getApplicants().add(jobSeekerProfile);

            jobSeekerRepository.save(jobSeekerProfile);
            jobRepository.save(job);
        } else {

            throw new RuntimeException("Job or Job Seeker Profile not found");
        }
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
    public List<Job> getPostedJobs(int empId){
        return jobRepository.findByEmployerProfileId(empId);
    }
    public String removeJob(int jobId){
        try{
         jobRepository.deleteById(jobId);
           return "Successfull";
        }
        catch(Exception e){
            return "Something went wrong";
        }
    }

}
