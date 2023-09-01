package br.com.hearMeOut.authentication.domain.address;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    private long id;
    @Column(name = "house_number")
    private String number;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "province", nullable = false)
    private Province province;
    @Column(name = "complement")
    private String complement;

    public Address(AdressSignInData data) {
        this.number = data.number();
        this.street = data.street();
        this.city = data.city();
        this.cep = data.cep();
        this.province = data.province();
        this.complement = data.complement();
    }
}
