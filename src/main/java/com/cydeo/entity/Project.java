package com.cydeo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity{
    private String projectName;
    private String projectCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectDetails;
    private int completeTaskCounts;
    private int unfinishedTaskCounts;

    @ManyToOne
    private User assignedManager;
}
