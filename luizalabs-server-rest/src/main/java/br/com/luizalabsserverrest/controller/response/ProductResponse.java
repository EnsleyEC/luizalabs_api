package br.com.luizalabsserverrest.controller.response;

import br.com.luizalabsserverrest.controller.request.ProductRequest;
import br.com.luizalabsserverrest.model.entity.ClientEntity;
import br.com.luizalabsserverrest.model.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductResponse {

    private Long id;
    private String title;
    private String image;
    private String brand;
    private Double price;
    private Double reviewScore;

    public static ProductResponse convertToResponse(ProductEntity productEntity)
    {
        ProductResponse response = new ProductResponse();

        response.setId(productEntity.getId());
        response.setTitle(productEntity.getTitle());
        response.setImage(productEntity.getImage());
        response.setBrand(productEntity.getBrand());
        response.setPrice(productEntity.getPrice());
        response.setReviewScore(productEntity.getReviewScore());

        return response;

    }

    public static List<ProductResponse> convertToResponseList(List<ProductEntity> productEntityList)
    {
        return productEntityList.stream()
                .map(ProductResponse::convertToResponse)
                .collect(Collectors.toList());

    }


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
}
