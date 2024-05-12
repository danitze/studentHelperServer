package com.danilandreev.diploma.studenthelper.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateGroupDto {
    @Size(max = 10, message = "Назва групи має містити від 1 до 10 символів")
    @NotBlank(message = "Назва групи не може бути порожнім")
    private String name;
}
