package com.interview.tbv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Create by Jm on 2021-01-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Team {
    @Id
    private String _id;
    private String id;
    private String name;
    @JsonProperty("department_id")
    @Field("department_id")
    private String departmentId;

}
