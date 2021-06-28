package com.projects.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // added for removing hibernateLazyInitializer {} response
public class Project {
    @Id
    @GeneratedValue
    private Integer projectId;
    private String projectName;
    private String clientName;
    private String durationInDays;
    private String startDate;
    private String endDate;
}
