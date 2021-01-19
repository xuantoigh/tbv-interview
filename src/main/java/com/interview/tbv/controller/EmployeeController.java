package com.interview.tbv.controller;

import com.interview.tbv.model.Employee;
import com.interview.tbv.service.EmployeeService;
import com.interview.tbv.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.interview.tbv.utility.Constants.ResponseKey.LIST;

/**
 * Create by Jm on 2021-01-18
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> listEmployees(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "offset", defaultValue = "1500") int offset){
        if (offset <= 0)
            throw new IllegalArgumentException("offset must >= 0");
        if (page < 0)
            throw new IllegalArgumentException("page must >= 0");

        List<Employee> employees = employeeService.listEmployees(page, offset);
        return Utilities.generateSuccessResponse(LIST, employees);
    }
}
