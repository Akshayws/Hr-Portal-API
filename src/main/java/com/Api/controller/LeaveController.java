package com.Api.controller;


import com.Api.model.Leave;
import com.Api.service.LeaveService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployeeId(employeeId));
    }

    @PostMapping("/apply")
    public ResponseEntity<Leave> applyLeave(@RequestBody Leave leave) {
        return ResponseEntity.ok(leaveService.applyLeave(leave));
    }
}
