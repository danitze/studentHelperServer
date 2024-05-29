package com.danilandreev.diploma.studenthelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "university_class")
public class UniversityClass {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "university_class_id_seq")
    @SequenceGenerator(name = "university_class_id_seq", sequenceName = "university_class_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "series_id")
    private String seriesId;

    @Column(name = "discipline_name")
    private String disciplineName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "is_online")
    private Boolean isOnline;

    @Column(name = "place")
    private String place;

    @Column(name = "home_task")
    private String homeTask;

    @Column(name = "link")
    private String link;

    @ManyToMany
    private List<UniversityGroup> universityGroups;

    @ManyToOne
    private User lecturer;

}
