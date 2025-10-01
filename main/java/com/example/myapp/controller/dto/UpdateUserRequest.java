package com.example.myapp.controller.dto;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(@Nonnull Long id, @NotBlank @Nullable String username, @NotBlank @Nullable @Email String email) {}
