package com.example.insider_threat.controller;

import com.example.insider_threat.service.ThreatService;
import com.example.insider_threat.model.Threat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThreatController {

    @Autowired
    private ThreatService threatService;

    @GetMapping("/api/insider-threats")
    public ResponseEntity<List<Threat>> getActiveThreats() {
        List<Threat> activeThreats = threatService.getActiveThreats();
        return ResponseEntity.ok(activeThreats);
    }
}