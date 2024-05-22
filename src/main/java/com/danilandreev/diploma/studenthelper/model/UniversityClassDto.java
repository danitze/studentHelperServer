package com.danilandreev.diploma.studenthelper.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityClassDto {
    private Long id;
    private String seriesId;
    private String disciplineName;
    private Date startDate;
    private List<GroupDto> universityGroups;
    private UserDto lecturer;
    private Boolean isOnline;
    private String place;
}
