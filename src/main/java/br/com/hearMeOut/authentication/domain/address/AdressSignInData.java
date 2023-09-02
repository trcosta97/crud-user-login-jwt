package br.com.hearMeOut.authentication.domain.address;

public record AdressSignInData(
        String number,
        String street,
        String city,
        String cep,
        Province province,
        String complement

) {
}
