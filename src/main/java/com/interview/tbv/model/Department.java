package com.interview.tbv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Create by Jm on 2021-01-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "department")
public class Department {
    @Id
    private String _id;
    private String id;
    private String name;
    private String manager; // id of employee
}
