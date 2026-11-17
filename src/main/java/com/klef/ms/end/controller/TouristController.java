package com.klef.ms.end.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.ms.end.entity.Tourist;
import com.klef.ms.end.repository.TouristRepository;
import com.klef.ms.end.util.TokenUtil;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/tourist")
public class TouristController {
    private final TouristRepository touristRepository;
    private final TokenUtil tokenUtil;

    public TouristController(TouristRepository touristRepository, TokenUtil tokenUtil) {
        this.touristRepository = touristRepository;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping
    public ResponseEntity<?> createTourist(
            @RequestBody Tourist tourist,
            @Parameter(description = "Pre-generated access token")
            @RequestHeader(value = "X-Auth-Token", required = false) String token) {
        if (!tokenUtil.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Missing or invalid token"));
        }

        tourist.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(touristRepository.save(tourist));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTouristById(
            @PathVariable Long id,
            @Parameter(description = "Pre-generated access token")
            @RequestHeader(value = "X-Auth-Token", required = false) String token) {
        if (!tokenUtil.isValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Missing or invalid token"));
        }

        return touristRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
