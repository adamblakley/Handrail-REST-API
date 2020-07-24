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
 * Geneate JWT and check received JWT
 */
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    /**
     * JWTSecret Application.properties
     */
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    /**
     * JWTExpirationInMs Applicaiton.properties
     */
    @Value("${app.jwtExpirationInMs}")
    private int jwtExperationInMs;

    /**
     * Generates token on request
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() +jwtExperationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * get user id from passed jwt
     * @param token
     * @return
     */
    public Integer getUserIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Integer.parseInt(claims.getSubject());
    }

    /**
     * authenticate token on request
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException s){
            logger.error("Invalid token signature");
        } catch (MalformedJwtException m){
            logger.error("Invalid token");
        } catch (ExpiredJwtException e){
            logger.error("Expired token");
        } catch (UnsupportedJwtException u){
            logger.error("Unsupport token");
        } catch (IllegalArgumentException i){
            logger.error("Token string unrecognised");
        }
        return false;
    }
}
