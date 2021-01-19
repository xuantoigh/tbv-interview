package com.interview.tbv.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.tbv.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by JM on 08/20/2019
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String _id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    private Set<Role> roles;

    public org.springframework.security.core.userdetails.User getUserDetails() {
        List<SimpleGrantedAuthority> authorities = this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(this.userName, this.password, authorities);
    }

    public void addRole(Role role) {
        if (Objects.isNull(this.roles))
            this.roles = new HashSet<>();
        this.roles.add(role);
    }

}
