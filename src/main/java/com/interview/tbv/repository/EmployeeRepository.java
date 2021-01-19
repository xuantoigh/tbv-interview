package com.interview.tbv.repository;

import com.interview.tbv.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Create by Jm on 2021-01-18
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findById(String id);

    List<Employee> findByIdIn(List<String> ids);
}
