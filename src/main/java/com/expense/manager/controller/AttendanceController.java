package com.expense.manager.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.manager.model.Attendance;
import com.expense.manager.model.Employee;
import com.expense.manager.model.WorkType;
import com.expense.manager.repository.EmployeeRepository;
import com.expense.manager.service.AttendanceService;
import com.expense.manager.service.EmployeeService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttendanceService attendanceService;

    // Load Attendance Page
    @GetMapping
    public String showAttendancePage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Attendance> todayAttendance = attendanceService.getTodayAttendance();
        
        Map<Long, Attendance> attendanceMap = todayAttendance.stream()
                .collect(Collectors.toMap(att -> att.getEmployee().getId(), att -> att));
        
        model.addAttribute("employees", employees);

        model.addAttribute("attendanceMap", attendanceMap);

        return "attendance";  // attendance.html page
    }

    // Mark Employee as Present
    @PostMapping("/mark-present")
    @ResponseBody
    public ResponseEntity<String> markPresent(@RequestParam Long employeeId) {
        attendanceService.markPresent(employeeId);
        return ResponseEntity.ok("Attendance marked as Present");
    }

    // Remove Attendance When Changed to Absent
    @PostMapping("/mark-absent")
    @ResponseBody
    public ResponseEntity<String> markAbsent(@RequestParam Long employeeId) {
        attendanceService.removeAttendance(employeeId);
        return ResponseEntity.ok("Attendance removed");
    }
}
