package br.com.luizalabsserverrest.controller.request;
import java.util.Set;

public class FavoriteProductsRequest {

    private Long clientId;
    private Set<Long> favoriteProductsIds;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Set<Long> getFavoriteProductsIds() {
        return favoriteProductsIds;
    }

    public void setFavoriteProductsIds(Set<Long> favoriteProductsIds) {
        this.favoriteProductsIds = favoriteProductsIds;
    }
}
