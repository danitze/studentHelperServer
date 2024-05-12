package com.danilandreev.diploma.studenthelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "university_group")
public class UniversityGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "university_group_id_seq")
    @SequenceGenerator(name = "university_group_id_seq", sequenceName = "university_group_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "universityGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> students;
}
