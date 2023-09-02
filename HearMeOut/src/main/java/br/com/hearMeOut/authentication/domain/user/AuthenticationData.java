package br.com.hearMeOut.authentication.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
