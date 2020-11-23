package br.com.luizalabsserverrest.controller.request;

import java.util.Set;

public class FavoriteProductsRequest {

    private Long clientId;
    private Set<Long> productsIds;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Set<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(Set<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
