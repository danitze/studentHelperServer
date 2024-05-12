package com.danilandreev.diploma.studenthelper.controller;

import com.danilandreev.diploma.studenthelper.model.AddUserToGroupDto;
import com.danilandreev.diploma.studenthelper.model.CreateGroupDto;
import com.danilandreev.diploma.studenthelper.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping()
    ResponseEntity<?> createGroup(
            @RequestBody CreateGroupDto dto
    ) {
        return ResponseEntity.ok(groupService.createGroup(dto));
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getGroup(
            @PathVariable("id") Long groupId
    ) {
        return ResponseEntity.ok(groupService.getGroup(groupId));
    }

    @PostMapping("/addstudent")
    ResponseEntity<?> addUserToGroup(
            @RequestBody AddUserToGroupDto dto
    ) {
        return ResponseEntity.ok(groupService.addUserToGroup(dto));
    }

}
