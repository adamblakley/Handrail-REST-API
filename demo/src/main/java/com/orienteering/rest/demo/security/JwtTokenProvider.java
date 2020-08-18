package com.orienteering.rest.demo.security;

import com.orienteering.rest.demo.security.models.UserPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Support class to generate a JWt and check a passed JWT
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    /**
     * JWTSecret resides within application.properties
     */
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    /**
     * JWTExpirationInMs resides within application.properties
     */
    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    /**
     * Generates token on a authenticated service request
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * get userId from a passed JWT string
     * @param token
     * @return
     */
    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * authenticate token on request from the controller
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException s){
            logger.error("Error: Invalid token signature");
        } catch (MalformedJwtException m){
            logger.error("Error: Invalid token passed");
        } catch (ExpiredJwtException e){
            logger.error("Error: The token has expired");
        } catch (UnsupportedJwtException u){
            logger.error("Error: The token is unsupported type");
        } catch (IllegalArgumentException i){
            logger.error("Error: Unrecognized token string");
        }
        return false;
    }
}
