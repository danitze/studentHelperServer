package com.danilandreev.diploma.studenthelper.model;

import lombok.Data;

@Data
public class AddUserToGroupDto {
    private Long userId;
    private Long groupId;
}
