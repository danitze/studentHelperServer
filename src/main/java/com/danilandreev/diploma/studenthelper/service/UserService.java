package com.danilandreev.diploma.studenthelper.service;

import com.danilandreev.diploma.studenthelper.model.GroupDto;
import com.danilandreev.diploma.studenthelper.model.User;
import com.danilandreev.diploma.studenthelper.model.UserDto;
import com.danilandreev.diploma.studenthelper.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }


    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            // Заменить на свои исключения
            throw new RuntimeException("Користувач з таким ім'ям вже існує");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Користувач з таким email вже існує");
        }

        return save(user);
    }

    @Transactional
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Transactional
    public UserDto getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByUsername(username)
                .map((user) -> UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .group(
                                Optional.ofNullable(user.getUniversityGroup())
                                        .map((group) -> GroupDto.builder()
                                                .id(group.getId())
                                                .name(group.getName())
                                                .build()).orElse(null)
                        )
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));
    }

    @Transactional
    public UserDto getById(Long id) {
        return repository.findById(id)
                .map((user) -> UserDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .group(GroupDto.builder()
                                .id(user.getUniversityGroup().getId())
                                .name(user.getUniversityGroup().getName())
                                .build()
                        )
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Користувача не знайдено"));
    }
}
