package com.example.insider_threat.service;

import com.example.insider_threat.model.Threat;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThreatService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ML_API_URL = "http://localhost:5000/api/predict-threats"; // Update port if changed

    public List<Threat> getActiveThreats() {
        try {
            // Attempt to fetch data from Python ML service
            Threat[] threatsArray = restTemplate.getForObject(ML_API_URL, Threat[].class);
            List<Threat> threats = (threatsArray != null) ? List.of(threatsArray) : new ArrayList<>();

            // Filter and sort
            List<Threat> activeThreats = new ArrayList<>();
            for (Threat threat : threats) {
                if (threat.getRiskScore() > 25 || threat.isAnomaly()) {
                    activeThreats.add(threat);
                }
            }
            activeThreats.sort((a, b) -> Double.compare(b.getRiskScore(), a.getRiskScore()));
            return activeThreats.isEmpty() ? generateFallbackData() : activeThreats;
        } catch (ResourceAccessException e) {
            System.err.println("Failed to connect to ML service at " + ML_API_URL + ". Using fallback data: " + e.getMessage());
            return generateFallbackData();
        }
    }

    private List<Threat> generateFallbackData() {
        List<Threat> fallbackThreats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Threat threat = new Threat(
                String.valueOf(i),
                "User_" + i,
                Math.random(),
                Math.random() * 45 + 5, // Random risk score between 5 and 50
                Math.random() > 0.7,    // 30% chance of anomaly
                Math.random() > 0.5 ? "High" : "Low",
                List.of("Fallback factor"),
                "Monitor"
            );
            fallbackThreats.add(threat);
        }
        return fallbackThreats;
    }
}