package com.jwt.example.model;

import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String passwordHash;
    private Set<String> roles; // ADMIN, TEACHER, STUDENT

    // getters + setters
}
