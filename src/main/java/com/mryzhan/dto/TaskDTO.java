package com.mryzhan.dto;

import com.mryzhan.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
public class TaskDTO {

    private Long id;

    @NotNull
    private ProjectDTO project;

    @NotNull
    private UserDTO assignedEmployee;

    @NotBlank
    private String taskSubject;

    @NotBlank
    private String taskDetail;

    private Status taskStatus;
    private LocalDate assignedDate;

    public TaskDTO(Long id, ProjectDTO project, UserDTO assignedEmployee, String taskSubject, String taskDetail, Status taskStatus, LocalDate assignedDate) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.project = project;
        this.assignedEmployee = assignedEmployee;
        this.taskSubject = taskSubject;
        this.taskDetail = taskDetail;
        this.taskStatus = taskStatus;
        this.assignedDate = assignedDate;
    }
}
