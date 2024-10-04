package com.example.Jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerDto {
    private String name;
    private String email;
    private String Skills;
    private String  resumeLink;

}
