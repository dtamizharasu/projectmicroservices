package com.projects.service;

import com.projects.model.Employee;
import com.projects.model.Project;
import com.projects.repository.ProjectRepository;
import com.projects.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    @Autowired
    RestTemplate restTemplate;

    public Set<ResponseVo> findAllProjects(){
        Set<ResponseVo> projDetails = new HashSet<>();
        Set<Employee> employees = new HashSet<>();
        repo.findAll().forEach(proj -> {
            String message = "No Employee Available in Project";
            ResponseVo responseVo = new ResponseVo();
            Employee employee;
            ResponseEntity<Integer[]> response =
                    restTemplate.getForEntity(
                            "http://Allocation-Service/api/allocation/employeeIds/"+proj.getProjectId(),
                            Integer[].class);
            Integer[] empIds = response.getBody();

            if(empIds!=null){
                for(Integer id: empIds){
                    employee = restTemplate
                            .getForObject("http://Employee-Service/api/employee/"+id,Employee.class);
                    employees.add(employee);
                }
                responseVo.setEmployees(employees);
                message = "Available Employees in Project";
            }
            responseVo.setProject(proj);
            responseVo.setEmployeeDetails(message);
            projDetails.add(responseVo);
        });

        return projDetails;
    }

    public ResponseVo getByProjectId(Integer id){
        Set<Employee> employees = new HashSet<>();
        ResponseVo responseVo = new ResponseVo();
        String message = "No Employee Available in Project";
        Project project = repo.getById(id);
        responseVo.setProject(project);
        ResponseEntity<int[]> response =
                restTemplate.getForEntity("http://Allocation-Service/api/allocation/employeeIds/"+id,int[].class);
        int[] empIds = response.getBody();

        if(empIds!=null){
            for(Integer ids: empIds){
                Employee employee = restTemplate
                        .getForObject("http://Employee-Service/api/employee/"+ids,Employee.class);
                employees.add(employee);
            }
            message = "Available Employees in Project";
            responseVo.setEmployees(employees);
        }
        responseVo.setEmployeeDetails(message);

        return responseVo;
    }

    public Project getProjectID(Integer id){
        return repo.getById(id);
    }

    public Project updateProject(Project project){
        return repo.save(project);
    }

    public Project saveProject(Project project){
        return repo.save(project);
    }

    public void deleteProjectById(Integer id){
        repo.deleteById(id);
    }

    public List<Project> getAllProjects(){
        return repo.findAll();
    }
}
