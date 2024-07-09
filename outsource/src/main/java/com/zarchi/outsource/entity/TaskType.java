package com.zarchi.outsource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TASK_TYPE")
public class TaskType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TASK_TYPE_CODE")
    private String taskTypeCode;

    @Column(name= "TASK_TYPE_NAME")
    private String taskTypeName;

    @Column(name= "SKILL")
    private String skill;

    @Column(name= "DESCRIPTION")
    private String description;

    @Column(name= "EXAMPLE")
    private String example;

}
