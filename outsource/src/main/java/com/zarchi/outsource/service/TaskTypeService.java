package com.zarchi.outsource.service;

import com.zarchi.outsource.entity.TaskType;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskTypeService {
    ResponseEntity<List<TaskType>> importTaskType (MultipartFile files);

    ResponseEntity<TaskType> createTaskType(TaskType taskType);
    Page<TaskType> getAllTaskTypeList(Integer offset, Integer limit);
    ResponseEntity<TaskType> getTaskTypeById(Long id);

    ResponseEntity<TaskType> updateTaskType(Integer id, TaskType taskType);
    ResponseEntity<String>deleteTaskType(Long id);
    ResponseEntity<String>deleteAllTaskType();



}
