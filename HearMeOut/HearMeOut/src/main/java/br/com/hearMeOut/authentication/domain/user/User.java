package br.com.hearMeOut.authentication.domain.user;

import br.com.hearMeOut.authentication.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "User")
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cpf", nullable = false, unique = true, updatable = false )
    private String cpf;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column (name = "password", nullable = false)
    private String password;
    @JoinColumn(name = "address_id", nullable = false)
    @OneToOne
    @JsonBackReference
    private Address address;
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "sign_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime signDate;
    @Column(name="status",columnDefinition = "NUMBER(1)")
    private Boolean status;

    public User(UserSignInData data) {
        this.name = data.name();
        this.cpf = data.cpf();
        this.email = data.email();
        this.password = data.password();
        this.address = new Address(data.address());
        this.gender = data.gender();

    }

    @PrePersist
    public void prePersist(){
        this.signDate = LocalDateTime.now();
        this.status = true;
    }

    @Transient
    public Integer getStatusAsInteger() {
        return status ? 1 : 0;
    }




}


