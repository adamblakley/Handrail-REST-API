package com.orienteering.rest.demo.security;

import com.orienteering.rest.demo.security.services.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Extension of OncePerRequestFilter, authentication filter to filter requests before processing by the service
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Token provider for JWT
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Spring Security UserDetailsService
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Logger object to log errors
    private static final Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    /**
     * Grab the jwt from the authorization header within the request
     * @param httpServletRequest
     * @return
     */
    private String getJwtFromRequest(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Get the userId from from the JWT Header and set the authentication in the security context before proceeding through filter
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = getJwtFromRequest(httpServletRequest);
            if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
                Long userId = jwtTokenProvider.getUserIdFromJWT(jwt);

                UserDetails userDetails = customUserDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            logger.error("Unable to set the requested authentication in security", e);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
