package com.example.Jobportal.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String position;
    private String location;
    private int experience;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_profile_id")
    @JsonIgnore
    private EmployerProfile employerProfile;

    @ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<JobSeekerProfile> applicants;
}
