package com.interview.tbv.controller;

import com.interview.tbv.Application;
import com.interview.tbv.model.Employee;
import com.interview.tbv.service.EmployeeService;
import com.interview.tbv.utility.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.interview.tbv.utility.Constants.ResponseKey.LIST;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;


/**
 * Create by Jm on 2021-01-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
public class EmployeeControllerTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController;



    @Test
    public void listEmployees(){
        //init sample data
        Employee employee = Employee.builder()
                .id("EMP-1")
                .name("James")
                .isDirector(true)
                .build();

        Employee employee2 = Employee.builder()
                .id("EMP-2")
                .name("Fiona")
                .teamIds(Arrays.asList("T-1", "T-2"))
                .isDirector(false)
                .build();

        List<Employee> employees = asList(employee, employee2);

        //save data to db
        employees = employeeService.saveAll(employees);

        Map<String, Object> expectedResponse = Utilities.generateSuccessResponse(LIST, employees);
        int offset = 1500;
        int page = 0;

        Map<String, Object> result = employeeController.listEmployees(page, offset);

        assertEquals(expectedResponse, result);
    }

}