package ru.kalach.simple_proxy.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ApiController {
    @GetMapping("/health")
    public ResponseEntity<Map<String, Boolean>> health() {
        return ResponseEntity.ok(Map.of("status", true));
    }
}