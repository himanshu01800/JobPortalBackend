package com.example.Jobportal.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDto {
    private String name;
    private String webSiteUrl;
    private String address;
    private String email;
}
