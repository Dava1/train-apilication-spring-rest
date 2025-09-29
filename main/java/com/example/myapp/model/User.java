package com.example.myapp.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    @NotBlank
    @JsonProperty("userName")
    private String userName;
    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    @JsonProperty("email")
    private String email;
    @Column(name = "created_at", nullable = false)
    @JsonProperty("created_at")
    private LocalDate createdAt;
}
