package com.danilandreev.diploma.studenthelper.service;

import com.danilandreev.diploma.studenthelper.model.*;
import com.danilandreev.diploma.studenthelper.repository.GroupRepository;
import com.danilandreev.diploma.studenthelper.repository.UniversityClassRepository;
import com.danilandreev.diploma.studenthelper.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UniversityClassService {
    private final UserRepository userRepository;
    private final UniversityClassRepository classRepository;
    private final GroupRepository groupRepository;

    @Transactional
    public String createClassSeries(
            CreateClassSeriesDto dto
    ) {
        String seriesId = UUID.randomUUID().toString();
        User lecturer = userRepository.findById(dto.getLecturerId())
                .orElseThrow(() -> new RuntimeException("Викладача не знайдено"));
        List<Long> groupIds = dto.getGroupIds();
        if (groupIds == null) {
            groupIds = List.of();
        }
        List<UniversityGroup> groups = groupIds.stream().map(groupRepository::findById).map((groupOptional) ->
                groupOptional.orElseThrow(() -> new RuntimeException("Групу не знайдено"))
        ).toList();

        Queue<StartDateWithIntervalDto> dates = new PriorityQueue<>(Comparator.comparing(StartDateWithIntervalDto::getStartDate));
        for (StartDateWithIntervalDto dateWithInterval : dto.getStartDatesWithIntervals()) {
            dates.offer(dateWithInterval);
        }
        for (int i = 0; i < dto.getClassesAmount(); ++i) {
            StartDateWithIntervalDto date = dates.poll();
            UniversityClass universityClass = UniversityClass.builder()
                    .seriesId(seriesId)
                    .disciplineName(dto.getDisciplineName())
                    .startDate(
                            date.getStartDate()
                    )
                    .universityGroups(groups)
                    .lecturer(lecturer)
                    .build();
            dates.offer(
                    new StartDateWithIntervalDto(
                            new Date(date.getStartDate().getTime() + date.getInterval()),
                            date.getInterval()
                    )
            );
            classRepository.save(universityClass);
        }
        return "Success";
    }

    public UniversityClassDto createClass(
            CreateClassDto dto
    ) {
        String seriesId;
        if (dto.getSeriesId() != null) {
            seriesId = dto.getSeriesId();
        } else {
            seriesId = UUID.randomUUID().toString();
        }
        User lecturer = userRepository.findById(dto.getLecturerId())
                .orElseThrow(() -> new RuntimeException("Викладача не знайдено"));
        List<Long> groupIds = dto.getGroupIds();
        if (groupIds == null) {
            groupIds = List.of();
        }
        List<UniversityGroup> groups = groupIds.stream().map(groupRepository::findById).map((groupOptional) ->
                groupOptional.orElseThrow(() -> new RuntimeException("Групу не знайдено"))
        ).toList();
        UniversityClass universityClass = UniversityClass.builder()
                .seriesId(seriesId)
                .disciplineName(dto.getDisciplineName())
                .startDate(dto.getStartDate())
                .universityGroups(groups)
                .lecturer(lecturer)
                .build();
        universityClass = classRepository.save(universityClass);
        return UniversityClassDto.builder()
                .id(universityClass.getId())
                .seriesId(universityClass.getSeriesId())
                .disciplineName(universityClass.getDisciplineName())
                .startDate(universityClass.getStartDate())
                .universityGroups(
                        universityClass.getUniversityGroups().stream().map((group) ->
                                GroupDto.builder().id(group.getId()).name(group.getName()).build()
                        ).toList()
                )
                .lecturer(
                        UserDto.builder()
                                .id(lecturer.getId())
                                .username(lecturer.getUsername())
                                .email(lecturer.getEmail())
                                .role(lecturer.getRole())
                                .firstName(lecturer.getFirstName())
                                .lastName(lecturer.getLastName())
                                .group(null)
                                .build()
                )
                .build();
    }

    public List<UniversityClassDto> getAllClasses(
            Date fromDate,
            Date toDate
    ) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(("User not found")));
        List<UniversityClass> universityClasses = switch (user.getRole()) {
            case STUDENT -> classRepository.findUniversityClassesByStartDateBetweenAndUniversityGroupsContains(
                    fromDate,
                    toDate,
                    user.getUniversityGroup()
            );
            case TEACHER -> classRepository.findUniversityClassesByStartDateBetweenAndLecturer(
                    fromDate,
                    toDate,
                    user
            );
            case ADMIN -> List.of();
        };
        return universityClasses.stream().map((universityClass) -> UniversityClassDto.builder()
                .id(universityClass.getId())
                .seriesId(universityClass.getSeriesId())
                .disciplineName(universityClass.getDisciplineName())
                .startDate(universityClass.getStartDate())
                .universityGroups(
                        universityClass.getUniversityGroups().stream().map((group) ->
                                GroupDto.builder().id(group.getId()).name(group.getName()).build()
                        ).toList()
                )
                .lecturer(
                        UserDto.builder()
                                .id(universityClass.getLecturer().getId())
                                .username(universityClass.getLecturer().getUsername())
                                .email(universityClass.getLecturer().getEmail())
                                .role(universityClass.getLecturer().getRole())
                                .firstName(universityClass.getLecturer().getFirstName())
                                .lastName(universityClass.getLecturer().getLastName())
                                .group(null)
                                .build()
                )
                .build()).toList();
    }
}
