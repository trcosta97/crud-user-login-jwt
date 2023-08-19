package br.com.hearMeOut.authentication.domain.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(name = "number")
    private String number;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "state", nullable = false)
    private State state;
    @Column(name = "complement")
    private String complement;

    public Address(AdressSignInData data) {
        this.number = data.number();
        this.street = data.street();
        this.city = data.city();
        this.cep = data.cep();
        this.state = data.state();
        this.complement = data.complement();
    }
}
