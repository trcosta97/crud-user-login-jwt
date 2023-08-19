package br.com.hearMeOut.authentication.domain.address;

public record AdressSignInData(
        String number,
        String street,
        String city,
        String cep,
        State state,
        String complement

) {
}
