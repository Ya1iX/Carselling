package com.practice.carselling.controller;

import com.practice.carselling.entity.Ad;
import com.practice.carselling.model.Response;
import com.practice.carselling.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/ads")
@RequiredArgsConstructor
public class AdsController {

    private final AdService service;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .data(Map.of("ads", service.readAll()))
                        .build()
        );
    }

    @PostMapping
    private ResponseEntity<Response> createAd(@RequestBody @Valid Ad ad) {
        service.save(ad);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .message("New ad created")
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .data(Map.of("ad", service.readById(id)))
                        .build()
        );
    }
}
