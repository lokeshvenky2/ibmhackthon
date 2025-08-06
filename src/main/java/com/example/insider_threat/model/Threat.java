package com.example.insider_threat.model;

import java.util.List;

public class Threat {
    private String id;
    private String entity;
    private double anomalyScore;
    private int riskScore;
    private List<String> contributingFactors;
    private String recommendation;

    // Default constructor
    public Threat() {}

    // Parameterized constructor
    public Threat(String id, String entity, double anomalyScore, int riskScore, 
                  List<String> contributingFactors, String recommendation) {
        this.id = id;
        this.entity = entity;
        this.anomalyScore = anomalyScore;
        this.riskScore = riskScore;
        this.contributingFactors = contributingFactors;
        this.recommendation = recommendation;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }

    public double getAnomalyScore() { return anomalyScore; }
    public void setAnomalyScore(double anomalyScore) { this.anomalyScore = anomalyScore; }

    public int getRiskScore() { return riskScore; }
    public void setRiskScore(int riskScore) { this.riskScore = riskScore; }

    public List<String> getContributingFactors() { return contributingFactors; }
    public void setContributingFactors(List<String> contributingFactors) { this.contributingFactors = contributingFactors; }

    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}