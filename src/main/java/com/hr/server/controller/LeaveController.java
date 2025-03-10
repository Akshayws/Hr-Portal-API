package com.hr.server.controller;

import com.hr.server.dto.LeaveDTO;
import com.hr.server.model.Leave;
import com.hr.server.service.LeaveService;
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

    @PostMapping("/add")
    public ResponseEntity<?> applyLeave(@RequestBody LeaveDTO leaveDTO) {
        Leave appliedLeave = leaveService.applyLeave(leaveDTO);
        if (appliedLeave == null) {
            return ResponseEntity.badRequest().body("Invalid Employee ID or Email");
        }
        return ResponseEntity.status(201).body(appliedLeave);
    }

    @PutMapping("/approve/{leaveId}")
    public ResponseEntity<?> approveLeave(@PathVariable Long leaveId) {
        Leave approvedLeave = leaveService.approveLeave(leaveId);
        if (approvedLeave == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(approvedLeave);
    }

    @PutMapping("/reject/{leaveId}")
    public ResponseEntity<?> rejectLeave(@PathVariable Long leaveId) {
        Leave rejectedLeave = leaveService.rejectLeave(leaveId);
        if (rejectedLeave == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rejectedLeave);
    }

    @GetMapping
    public ResponseEntity<List<LeaveDTO>> getAllLeaves() {
        List<LeaveDTO> leaves = leaveService.getAllLeaves();
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<?> getLeaveById(@PathVariable Long leaveId) {
        try {
            LeaveDTO leave = leaveService.getLeaveById(leaveId);
            return ResponseEntity.ok(leave);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<LeaveDTO>> getLeavesByEmployee(@RequestParam String email) {
        List<LeaveDTO> leaves = leaveService.getLeavesByEmployeeEmail( email);
        if (leaves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(leaves);
    }
}