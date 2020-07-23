package com.orienteering.rest.demo.security.payloads;

public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    /**
     * default constructor
     */
    public JwtAuthenticationResponse() {
    }

    /**
     * Constructor with parameters
     * @param accessToken
     */
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken=accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
