package com.projects.vo;

import com.projects.model.Employee;
import com.projects.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo {

    private Project project;
    private String employeeDetails;
    private Set<Employee> employees;
}
