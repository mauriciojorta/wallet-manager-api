package com.wallet.manager.controller.constant;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Constants");
    }

    public static final String API_CUSTOMERS = "/customers";
    public static final String API_WALLETS = "/wallets";
    public static final String API_WALLETS_TRANSFER = "/transfer";
    public static final String ID = "/{id}";
    
    public static final String ALLOWED_CORS_URL = "http://localhost:3000";


    public static final String OK_CODE_RESPONSE = "200";
    public static final String BAD_REQUEST_CODE_RESPONSE = "400";
    public static final String NOT_FOUND_CODE_RESPONSE = "404";
    
    public static final String OK_RESPONSE = "OK";
    public static final String BAD_REQUEST_RESPONSE = "Bad request";
    public static final String NOT_FOUND_RESPONSE = "Not found";

}
