package com.orienteering.rest.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * Class determines the response to unauthorized access
 */
@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint {

    // Logger used to document error
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);


    /**
     * The response issued on unauthorized access
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.error("Error {}",e.getMessage());
        httpServletResponse.sendError(httpServletResponse.SC_UNAUTHORIZED,e.getMessage());
    }
}
