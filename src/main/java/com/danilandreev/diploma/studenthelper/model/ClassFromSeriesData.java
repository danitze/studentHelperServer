package com.danilandreev.diploma.studenthelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassFromSeriesData {
    private Date startDate;
    private Long interval;
    private Boolean isOnline;
    private String place;
}
