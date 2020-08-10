package com.orienteering.rest.demo.security.payloads;

public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private Long userId;

    /**
     * default constructor
     */
    public JwtAuthenticationResponse() {
    }

    /**
     * Constructor with parameters
     * @param accessToken
     */
    public JwtAuthenticationResponse(String accessToken, Long userId) {
        this.accessToken=accessToken;
        this.userId=userId;
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

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }
}