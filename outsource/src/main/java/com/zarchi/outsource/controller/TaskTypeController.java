package com.zarchi.outsource.controller;

import com.zarchi.outsource.entity.TaskType;
import com.zarchi.outsource.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/task-types")
public class TaskTypeController {
    @Autowired
    TaskTypeService taskTypeService;
    @PostMapping(value = "/import-excel")
    public ResponseEntity<List<TaskType>>importExcel(@RequestParam ("file") MultipartFile files)throws Exception{
        return taskTypeService.importTaskType(files);
    }

    @PostMapping(value = "/create-task-type")
    public ResponseEntity<TaskType>createTaskType(@RequestBody TaskType taskType){
        return taskTypeService.createTaskType(taskType);
    }

    @GetMapping(value = "/get-all-task-types")
    public ResponseEntity<Page<TaskType>> getTaskTypesList(@Validated @RequestParam (defaultValue = "0") Integer offset,
                                                           @Validated @RequestParam (defaultValue = "10") Integer limit){
    Page<TaskType> taskTypesList = taskTypeService.getAllTaskTypeList(offset,limit);
    return ResponseEntity.ok(taskTypesList);
    }

    @GetMapping(value = "/get-task-type-by-id/{id}")
    public ResponseEntity<TaskType> getTaskTypeById(@Validated @PathVariable Long id){
        return taskTypeService.getTaskTypeById(id);
    }

    @PostMapping(value = "/update-task-type/{id}")
    public ResponseEntity<TaskType> updateTaskType(@Validated @PathVariable Integer id,
                                                   @RequestBody TaskType taskType){
        return taskTypeService.updateTaskType(id, taskType);
    }

    @DeleteMapping(value = "/delete-task-type/{id}")
    public ResponseEntity<String> deleteTaskType(@Validated @PathVariable Long id){
        return taskTypeService.deleteTaskType(id);
    }

    @DeleteMapping(value = "/delete-all-task-types")
    public ResponseEntity<String> deleteAllTaskTypes(){
        return taskTypeService.deleteAllTaskType();
    }
}
