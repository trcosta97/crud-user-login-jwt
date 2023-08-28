package br.com.hearMeOut.authentication.domain.user;

import br.com.hearMeOut.authentication.domain.address.AdressSignInData;

import java.time.LocalDateTime;

public record UserSignInData(
        String name,
        String cpf,
        String email,
        String password,
        AdressSignInData address,
        Gender gender


) {
}
