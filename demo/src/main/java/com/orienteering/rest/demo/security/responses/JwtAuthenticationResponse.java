package com.orienteering.rest.demo.security.responses;

/**
 * token response class provides JWT token string, token type and associated user id for saving within the client
 */
public class JwtAuthenticationResponse {

    // JWt access token string
    private String accessToken;
    // Token type for auth header
    private String tokenType = "Bearer";
    // Asssociated user id
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
