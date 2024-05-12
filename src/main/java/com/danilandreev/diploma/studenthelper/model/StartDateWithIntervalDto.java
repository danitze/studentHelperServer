package com.danilandreev.diploma.studenthelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartDateWithIntervalDto {
    private Date startDate;
    private Long interval;
}
