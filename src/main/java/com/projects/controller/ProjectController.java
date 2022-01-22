package com.projects.controller;

import com.projects.model.Project;
import com.projects.service.ProjectService;
import com.projects.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/*
 * Project Microservices APIs
 * 1. Get All Project Details
 * 2. Get Project with Employees details By Id
 * 3. Get the Project Details
 * 4. Add New Project
 * 5. Update Project Details
 * 6. Delete Project Details
 * */

// Swagger URL - http://localhost:8082/swagger-ui/

@Api(value = "Project", tags = {"Project"})
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    ProjectService service;

    /*
     * Get All the Project Details
     * @return - Return List Of Projects
     */
    @ApiOperation(value = "Get All Projects with Mapped Employees Details",
            response = ResponseVo.class, tags = "Project")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/allprojects")
    public Set<ResponseVo> getAllProject(){
        return service.findAllProjects();
    }

    /**
     * Get Project Record with employees BY Id
     * @param id - ID for the Project
     * @return - Project
     */
    @ApiOperation(value = "Get Single Projects with Mapped Employees Details",
            response = ResponseVo.class, tags = "Project")
    @GetMapping("/project/{id}")
    public ResponseVo getProjectId(@PathVariable Integer id){
        return service.getByProjectId(id);
    }

    /**
     * Get Project Record BY Id
     * @param id - ID for the Project
     * @return - Project
     */
    @ApiOperation(value = "Get Single Projects Details",
            response = Project.class, tags = "Project")
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Integer id){
        return service.getProjectID(id);
    }

    /**
     * @param project - Project Records
     * @return - Saved Project Record
     */
    @ApiOperation(value = "Add New Project Details",
            response = Project.class, tags = "Project")
    @PostMapping("/saveproject")
    public Project saveEmployee(@RequestBody Project project){
        return service.saveProject(project);
    }

    /**
     * @param project - Project Records
     * @return - Updated Project Record
     */
    @ApiOperation(value = "Update Project Details",
            response = Project.class, tags = "Project")
    @PutMapping("/updateproject")
    public Project updateEmployee(@RequestBody Project project){
        return service.updateProject(project);
    }

    /**
     * @param id - Project Id
     * @return - Deleted Message
     */
    @ApiOperation(value = "Delete Project Details",
            response = Project.class, tags = "Project")
    @DeleteMapping("/{id}")
    public String deleteProjectById(@PathVariable Integer id){
        service.deleteProjectById(id);
        return "Project Details Deleted";
    }

    @GetMapping("/get/all/projects")
    public List<Project> findAllProject(){
        return service.getAllProjects();
    }
}
