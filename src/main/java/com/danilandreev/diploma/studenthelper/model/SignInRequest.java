package com.danilandreev.diploma.studenthelper.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {

    @Size(min = 5, max = 50, message = "Ім'я користувача має містити від 5 до 50 символів")
    @NotBlank(message = "Ім'я користувача не може бути порожнім")
    private String username;

    @Size(min = 8, max = 255, message = "Довжина пароля має бути від 8 до 255 символів")
    @NotBlank(message = "Пароль не може бути порожнім")
    private String password;
}
