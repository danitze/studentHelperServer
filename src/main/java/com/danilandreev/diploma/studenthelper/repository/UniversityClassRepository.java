package com.danilandreev.diploma.studenthelper.repository;

import com.danilandreev.diploma.studenthelper.model.UniversityClass;
import com.danilandreev.diploma.studenthelper.model.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UniversityClassRepository extends JpaRepository<UniversityClass, Long> {

    @Query(value = "SELECT * FROM university_class u WHERE u.start_date > ?1 AND u.start_date < ?2 AND u.lecturer_id = ?3", nativeQuery = true)
    List<UniversityClass> findUniversityClassesForLecturer(
            Date startDate,
            Date endDate,
            Long lecturerId
    );

    List<UniversityClass> findUniversityClassesByStartDateBetweenAndUniversityGroupsContains(
            Date startDate,
            Date endDate,
            UniversityGroup universityGroup
    );

    List<UniversityClass> findAllBySeriesId(String seriesId);

}
