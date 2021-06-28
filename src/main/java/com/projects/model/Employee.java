package com.projects.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer empId;
    private String empName;
    private String designation;
    private String emailId;
    private String mobileNum;
    private String availability;
}
