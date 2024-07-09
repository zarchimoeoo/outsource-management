package com.zarchi.outsource.repository;

import com.zarchi.outsource.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepo extends JpaRepository<TaskType, Long> {
}
