package br.com.hearMeOut.authentication.domain.user;

import br.com.hearMeOut.authentication.domain.address.Address;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "User")
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
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
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


