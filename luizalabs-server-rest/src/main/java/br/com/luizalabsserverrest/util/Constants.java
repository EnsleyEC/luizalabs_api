package br.com.luizalabsserverrest.util;

public class Constants {

    // =================== ROUTES API ==================== //

    /* GENERAL */
    public final static String ROUTE_ID = "/{id}";
    public final static String ROUTE_SAVE = "/save";

    /* SIGN IN */
    public final static String ROUTE_SIGN_IN = "/api/v1/signIn";
    /* ACCOUNT */
    public final static String ROUTE_ACCOUNT = "/api/v1/account";
    /* CLIENT */
    public final static String ROUTE_CLIENT = "/api/v1/client";
    public final static String ROUTE_ADD_FAVORITE_PRODUCTS_BY_CLIENT = "/products";
    /* PRODUCT */
    public final static String ROUTE_PRODUCT = "/api/v1/product";
    public final static String ROUTE_FIND_FAVORITE_PRODUCTS_BY_CLIENT = "/client/{id}";

    // ============= JWT ============== //

    public final static String KEY_JWT = "luizalabs";

}
