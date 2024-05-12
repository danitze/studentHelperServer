package com.danilandreev.diploma.studenthelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassSeriesDto {
    private Long lecturerId;
    private List<StartDateWithIntervalDto> startDatesWithIntervals;
    private String disciplineName;
    private List<Long> groupIds;
    private Long classesAmount;
}
