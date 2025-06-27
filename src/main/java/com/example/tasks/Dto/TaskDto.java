package com.example.tasks.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private String title;
    private String description;
    private String status;
    private Long taskGroupId;
}