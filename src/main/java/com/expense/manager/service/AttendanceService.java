package com.expense.manager.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manager.model.Attendance;
import com.expense.manager.model.Employee;
import com.expense.manager.repository.AttendanceRepository;
import com.expense.manager.repository.EmployeeRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Mark Employee as Present
    public void markPresent(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LocalDate today = LocalDate.now();
        
        // Check if attendance already exists
        Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeAndDate(employee, today);
        
        if (existingAttendance.isEmpty()) {
            Attendance attendance = new Attendance();
            attendance.setEmployee(employee);
            attendance.setDate(LocalDate.now());
            attendance.setStatus(Attendance.Status.PRESENT);

            attendanceRepository.save(attendance);
            System.out.println("Saved Attendance for Employee: " + employeeId);
        } else {
            System.out.println("Attendance already marked for Employee: " + employeeId);
        }
    }

    public void removeAttendance(Long employeeId) {
        LocalDate today = LocalDate.now();
        attendanceRepository.deleteByEmployeeIdAndDate(employeeId, today);
    }


    public long getPresentEmployeeCount() {
        LocalDate today = LocalDate.now();
        return attendanceRepository.countByDateAndStatus(today, Attendance.Status.PRESENT);
    }


    // Fetch All Present Employees for Today
    public List<Attendance> getTodayAttendance() {
        LocalDate today = LocalDate.now();
        return attendanceRepository.findByDate(today)
                .stream()
                .filter(att -> att.getEmployee().isActive())  // Filter active employees only
                .collect(Collectors.toList());
    }

}
