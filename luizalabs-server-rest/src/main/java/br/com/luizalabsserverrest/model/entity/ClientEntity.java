package br.com.luizalabsserverrest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long id;

    @Column(name = "cli_name")
    private String name;

    @Column(name = "cli_email")
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_has_product",
            joinColumns = @JoinColumn(name = "chp_cli_id"),
            inverseJoinColumns = @JoinColumn(name = "chp_pro_id"))
    private List<ProductEntity> favoriteProductsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public List<ProductEntity> getFavoriteProductsList() {
        return favoriteProductsList;
    }

    public void setFavoriteProductsList(List<ProductEntity> favoriteProductsList) {
        this.favoriteProductsList = favoriteProductsList;
    }
}
