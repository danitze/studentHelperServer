package com.danilandreev.diploma.studenthelper.repository;

import com.danilandreev.diploma.studenthelper.model.UniversityClass;
import com.danilandreev.diploma.studenthelper.model.UniversityGroup;
import com.danilandreev.diploma.studenthelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UniversityClassRepository extends JpaRepository<UniversityClass, Long> {

    List<UniversityClass> findUniversityClassesByStartDateBetweenAndLecturer(
            Date startDate,
            Date endDate,
            User lecturer
    );

    List<UniversityClass> findUniversityClassesByStartDateBetweenAndUniversityGroupsContains(
            Date startDate,
            Date endDate,
            UniversityGroup universityGroup
    );

}
