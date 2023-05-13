package com.mryzhan.repository;

import com.mryzhan.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT count(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'Completed'")
    int totalNonCompletedTasks(String projectCode);

    @Query(value = "SELECT count(*)" +
            " FROM tasks t JOIN projects p ON t.project_id = p.id " +
            "WHERE p.project_code  = ?1 AND t.task_status = 'COMPLETE'", nativeQuery = true)
    int totalCompletedTasks(String projectCode);
}
