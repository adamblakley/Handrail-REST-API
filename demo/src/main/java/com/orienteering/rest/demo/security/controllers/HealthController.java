package com.orienteering.rest.demo.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class returns health check status
 */
@RestController
public class HealthController {

    /**
     * Returns HTTP Status 200
     * @return
     */
    @GetMapping("/healthcheck")
    public ResponseEntity<?> healthcheck(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
