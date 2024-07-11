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
public class EmployerProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String webSiteUrl;
  private String address;
  private String description;
  private int established;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  @OneToMany(mappedBy = "employerProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<Job> jobs;
}
