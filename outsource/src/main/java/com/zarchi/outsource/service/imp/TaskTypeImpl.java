package com.zarchi.outsource.service.imp;

import com.zarchi.outsource.entity.TaskType;
import com.zarchi.outsource.repository.TaskTypeRepo;
import com.zarchi.outsource.service.TaskTypeService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskTypeImpl implements TaskTypeService {
    @Autowired
    TaskTypeRepo taskTypeRepo;

    @Override
    public ResponseEntity<List<TaskType>> importTaskType(MultipartFile files) {
        List<TaskType> taskTypesList = new ArrayList<>();

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(files.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i= 4; i< worksheet.getPhysicalNumberOfRows(); i++){
            TaskType taskType = new TaskType();
            XSSFRow row =  worksheet.getRow(i);
            taskType.setTaskTypeCode(row.getCell(1).getStringCellValue());
            taskType.setTaskTypeName(row.getCell(2).getStringCellValue());
            taskType.setSkill(row.getCell(3).getStringCellValue());
            taskType.setDescription(row.getCell(4).getStringCellValue());
            taskType.setExample(row.getCell(5).getStringCellValue());
            taskTypesList.add(taskType);
            taskTypeRepo.saveAll(taskTypesList);
        }

        return new ResponseEntity<>(taskTypesList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskType> createTaskType(TaskType taskType) {
        TaskType taskType1 = new TaskType();
        taskType1.setTaskTypeCode(taskType.getTaskTypeCode());
        taskType1.setTaskTypeName(taskType.getTaskTypeName());
        taskType1.setSkill(taskType.getSkill());
        taskType1.setDescription(taskType.getDescription());
        taskType1.setExample(taskType.getExample());
        taskTypeRepo.save(taskType1);
        return ResponseEntity.ok(taskType1);
    }

    @Override
    public Page<TaskType> getAllTaskTypeList(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return taskTypeRepo.findAll(pageable);
    }

    @Override
    public ResponseEntity<TaskType> getTaskTypeById(Long id) {
        Optional<TaskType> taskTypeOptional = taskTypeRepo.findById(id);
        if(taskTypeOptional.isPresent()){
            TaskType taskTypeRes = new TaskType();
            taskTypeRes.setId(taskTypeOptional.get().getId());
            taskTypeRes.setTaskTypeCode(taskTypeOptional.get().getTaskTypeCode());
            taskTypeRes.setTaskTypeName(taskTypeOptional.get().getTaskTypeName());
            taskTypeRes.setSkill(taskTypeOptional.get().getSkill());
            taskTypeRes.setDescription(taskTypeOptional.get().getDescription());
            taskTypeRes.setExample(taskTypeOptional.get().getExample());
            return ResponseEntity.ok(taskTypeRes);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<TaskType> updateTaskType(Integer id, TaskType taskType) {
        Optional<TaskType> taskTypeOptional = taskTypeRepo.findById(id.longValue());
        if(taskTypeOptional.isPresent()){
            TaskType taskTypeRes = taskTypeOptional.get();
            taskTypeRes.setTaskTypeCode(taskType.getTaskTypeCode());
            taskTypeRes.setTaskTypeName(taskType.getTaskTypeName());
            taskTypeRes.setSkill(taskType.getSkill());
            taskTypeRes.setDescription(taskType.getDescription());
            taskTypeRes.setExample(taskType.getExample());
            taskTypeRepo.save(taskTypeRes);
            return ResponseEntity.ok(taskTypeRes);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteTaskType(Long id) {
        Optional<TaskType> taskTypeOptional = taskTypeRepo.findById(id);
        if(taskTypeOptional.isPresent()){
            taskTypeRepo.deleteById(Long.valueOf(id));
            return ResponseEntity.ok("Deleted the task type with the id " + id );
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<String> deleteAllTaskType() {
        taskTypeRepo.deleteAll();
        return ResponseEntity.ok("Deleted all task types successfully.");
    }
}
