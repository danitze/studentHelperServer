package com.danilandreev.diploma.studenthelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClassDto {
    private Long lecturerId;
    private Date startDate;
    private Long interval;
    private String disciplineName;
    private List<Long> groupIds;
    private String seriesId;
    private Boolean isOnline;
    private String place;
}
