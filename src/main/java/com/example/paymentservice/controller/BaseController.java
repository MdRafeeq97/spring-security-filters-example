package com.example.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class BaseController {
    @GetMapping
    public ResponseEntity<Object> login() {
        return ResponseEntity.ok().body("success");
    }
}
