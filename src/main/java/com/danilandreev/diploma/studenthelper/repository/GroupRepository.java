package com.danilandreev.diploma.studenthelper.repository;

import com.danilandreev.diploma.studenthelper.model.UniversityGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<UniversityGroup, Long> {
}
