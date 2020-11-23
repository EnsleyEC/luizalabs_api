package br.com.luizalabsserverrest.controller.request;

import br.com.luizalabsserverrest.model.entity.ProductEntity;

public class ProductRequest {

    private String title;
    private String image;
    private String brand;
    private Double price;
    private Double reviewScore;

    public static ProductEntity convertToEntity(ProductRequest productRequest)
    {
        ProductEntity entity = new ProductEntity();

        entity.setTitle(productRequest.getTitle());
        entity.setImage(productRequest.getImage());
        entity.setBrand(productRequest.getBrand());
        entity.setPrice(productRequest.getPrice());
        entity.setReviewScore(productRequest.getReviewScore());

        return entity;

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
