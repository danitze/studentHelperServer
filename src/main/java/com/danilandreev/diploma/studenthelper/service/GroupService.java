package com.danilandreev.diploma.studenthelper.service;

import com.danilandreev.diploma.studenthelper.model.*;
import com.danilandreev.diploma.studenthelper.repository.GroupRepository;
import com.danilandreev.diploma.studenthelper.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public GroupDto createGroup(CreateGroupDto dto) {
        UniversityGroup universityGroup = UniversityGroup.builder()
                .name(dto.getName())
                .students(List.of())
                .build();
        universityGroup = groupRepository.save(universityGroup);
        return GroupDto.builder()
                .id(universityGroup.getId())
                .name(universityGroup.getName())
                .build();
    }

    @Transactional
    public GroupDto getGroup(Long id) {
        return groupRepository.findById(id)
                .map(group -> GroupDto.builder()
                        .id(group.getId())
                        .name(group.getName())
                        .build())
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
    }

    public String addUserToGroup(AddUserToGroupDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));
        if (user.getRole() != Role.STUDENT) {
            throw new IllegalStateException("Користувач не є студентом");
        }
        UniversityGroup group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("Групу не знайдено"));
        group.getStudents().add(user);
        user.setUniversityGroup(group);
        groupRepository.save(group);
        return "Success";
    }

}
