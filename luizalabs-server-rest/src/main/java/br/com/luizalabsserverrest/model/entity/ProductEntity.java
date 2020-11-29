package br.com.luizalabsserverrest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_title", length = 50, columnDefinition = "CHARACTER VARYING")
    private String title;

    @Column(name = "pro_image",columnDefinition = "TEXT")
    private String image;

    @Column(name = "pro_brand",length = 50, columnDefinition = "CHARACTER VARYING")
    private String brand;

    @Column(name = "pro_price",columnDefinition = "REAL")
    private Double price;

    @Column(name = "pro_review_score",columnDefinition = "REAL")
    private Double reviewScore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_has_product",
            joinColumns = @JoinColumn(name = "chp_pro_id"),
            inverseJoinColumns = @JoinColumn(name = "chp_cli_id"))
    private List<ClientEntity> clientEntityList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Double reviewScore) {
        this.reviewScore = reviewScore;
    }

    @JsonIgnore
    public List<ClientEntity> getClientEntityList() {
        return clientEntityList;
    }

    public void setClientEntityList(List<ClientEntity> clientEntityList) {
        this.clientEntityList = clientEntityList;
    }
}
