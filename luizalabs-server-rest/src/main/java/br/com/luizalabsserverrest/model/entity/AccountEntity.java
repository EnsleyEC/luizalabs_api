package br.com.luizalabsserverrest.model.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    private Long id;

    @Column(name = "acc_username",nullable = true, unique = true,length = 20, columnDefinition = "CHARACTER VARYING")
    private String username;

    @Column(name = "acc_password",nullable = true, length = 80, columnDefinition = "CHARACTER VARYING")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
