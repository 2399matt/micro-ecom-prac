package com.example.auth.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(@NotNull(message = "Username required.") @Size(min=1, max=20, message= "Username must be between 1-20 characters.") String username,
                                  @NotNull(message = "Password required.") @Size(min=7, max=32, message = "Password must be between 7-32 characters.") String password) {
}
