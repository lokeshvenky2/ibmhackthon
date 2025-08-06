package com.example.insider_threat.controller;

import com.example.insider_threat.model.Threat;
import com.example.insider_threat.service.ThreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ThreatController {
    private final ThreatService threatService;

    @Autowired
    public ThreatController(ThreatService threatService) {
        this.threatService = threatService;
    }

    @GetMapping("/insider-threats")
    public List<Threat> getActiveInsiderThreats() {
        return threatService.getActiveThreats();
    }
}