package com.interview.tbv.service;

import com.interview.tbv.model.Employee;
import com.interview.tbv.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create by Jm on 2021-01-18
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listEmployees(int page, int offset) {
        Page<Employee> employees = employeeRepository.findAll(new PageRequest(page, offset));
        if (Objects.nonNull(employees))
            return employees.getContent();
        return new ArrayList<>();
    }

    public Employee save(Employee employee) {
        Optional<Employee> response = employeeRepository.findById(employee.getId());
        response.ifPresent(oldEmployee -> employee.set_id(oldEmployee.get_id()));
        return employeeRepository.save(employee);
    }

    public List<Employee> saveAll(List<Employee> employees) {
        List<String> ids = employees.stream().map(Employee::getId).collect(Collectors.toList());
        List<Employee> response = employeeRepository.findByIdIn(ids);
        if (Objects.nonNull(response)) {
            Map<String, Employee> employeeMap = new HashMap<>();
            response.forEach(employee -> employeeMap.putIfAbsent(employee.getId(), employee));
            employees = employees.stream()
                    .peek(employee -> {
                        if (employeeMap.containsKey(employee.getId())) {
                            Employee oldEmployee = employeeMap.get(employee.getId());
                            employee.set_id(oldEmployee.get_id());
                        }
                    })
                    .collect(Collectors.toList());
        }

        return employeeRepository.save(employees);
    }
}
