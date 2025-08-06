package com.example.insider_threat.service;

import com.example.insider_threat.model.Threat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ThreatService {
    private static final int THRESHOLD = 25;
    private static final int MIN_RISK = 5;
    private static final int MAX_RISK = 50;
    private final Random random = new Random();

    public List<Threat> getActiveThreats() {
        List<Threat> allEntities = generateMockEntities();
        List<Threat> activeThreats = new ArrayList<>();

        for (Threat entity : allEntities) {
            // Generate random anomaly score (simulating Isolation Forest output)
            double anomalyScore = random.nextDouble(); // 0.0 to 1.0
            
            // Convert anomaly score to risk score (5-50)
            int riskScore = MIN_RISK + (int)(anomalyScore * (MAX_RISK - MIN_RISK));
            
            // Update entity with scores
            entity.setAnomalyScore(anomalyScore);
            entity.setRiskScore(riskScore);
            
            // Generate contributing factors based on risk score
            entity.setContributingFactors(generateContributingFactors(riskScore));
            
            // Generate recommendation based on risk score
            entity.setRecommendation(generateRecommendation(entity.getEntity(), riskScore));
            
            // Filter active threats (risk score > threshold)
            if (riskScore > THRESHOLD) {
                activeThreats.add(entity);
            }
        }

        // Sort by risk score descending
        activeThreats.sort((a, b) -> Integer.compare(b.getRiskScore(), a.getRiskScore()));
        
        return activeThreats;
    }

    private List<Threat> generateMockEntities() {
        String[] entities = {
            "John Smith", "Emily Johnson", "Michael Brown", "Sarah Davis", 
            "David Wilson", "Lisa Miller", "James Moore", "Jennifer Taylor",
            "Robert Anderson", "Mary Thomas", "William Jackson", "Patricia White",
            "Thomas Harris", "Linda Martin", "Christopher Thompson", "Barbara Garcia",
            "Daniel Martinez", "Elizabeth Robinson", "Matthew Clark", "Susan Rodriguez"
        };

        List<Threat> mockEntities = new ArrayList<>();
        for (int i = 0; i < entities.length; i++) {
            mockEntities.add(new Threat(String.valueOf(i + 1), entities[i], 0.0, 0, new ArrayList<>(), ""));
        }
        return mockEntities;
    }

    private List<String> generateContributingFactors(int riskScore) {
        List<String> factors = new ArrayList<>();
        String[] allFactors = {
            "Unusual login times", "Access to sensitive data", "Large data transfers",
            "Multiple failed logins", "Privilege escalation attempts", "File access anomalies",
            "Network activity spikes", "Suspicious email patterns"
        };

        int factorCount = 1;
        if (riskScore > 40) {
            factorCount = 3;
        } else if (riskScore > 30) {
            factorCount = 2;
        }

        // Select random factors
        List<String> factorList = new ArrayList<>();
        for (int i = 0; i < factorCount; i++) {
            String factor;
            do {
                factor = allFactors[random.nextInt(allFactors.length)];
            } while (factorList.contains(factor));
            factorList.add(factor);
        }

        return factorList;
    }

    private String generateRecommendation(String entity, int riskScore) {
        if (riskScore >= 40) {
            return String.format("IMMEDIATE ACTION REQUIRED: Conduct security interview with %s and temporarily restrict access to sensitive systems", entity);
        } else if (riskScore >= 30) {
            return String.format("Investigate %s's recent access patterns and data transfers within 24 hours", entity);
        } else {
            return String.format("Monitor %s's activities for the next 48 hours and review recent file access history", entity);
        }
    }
}