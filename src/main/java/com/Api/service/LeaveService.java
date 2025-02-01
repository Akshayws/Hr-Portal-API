package com.Api.service;

import com.Api.model.Leave;
import com.Api.repository.LeaveRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getLeavesByEmployeeId(Long employeeId) {
        return leaveRepository.findByEmployee_EmployeeId(employeeId);
    }

    public Leave applyLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
}
