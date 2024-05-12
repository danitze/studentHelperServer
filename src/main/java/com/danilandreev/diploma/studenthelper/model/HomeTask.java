package com.danilandreev.diploma.studenthelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "home_task")
public class HomeTask {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "home_task_id_seq")
    @SequenceGenerator(name = "home_task_id_seq", sequenceName = "home_task_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private UniversityClass universityClass;

    private String description;
}
