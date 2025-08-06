package com.example.insider_threat.model;

import java.util.List;

public class Threat {
    private String id;
    private String entity;
    private double anomalyScore;
    private double riskScore;
    private boolean isAnomaly; // New field for Isolation Forest
    private String riskClass;  // New field for Random Forest
    private List<String> contributingFactors;
    private String recommendation;

    // Constructors, getters, setters
    public Threat() {}
    public Threat(String id, String entity, double anomalyScore, double riskScore, boolean isAnomaly, String riskClass, List<String> contributingFactors, String recommendation) {
        this.id = id;
        this.entity = entity;
        this.anomalyScore = anomalyScore;
        this.riskScore = riskScore;
        this.isAnomaly = isAnomaly;
        this.riskClass = riskClass;
        this.contributingFactors = contributingFactors;
        this.recommendation = recommendation;
    }

    // Getters and setters for all fields
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }
    public double getAnomalyScore() { return anomalyScore; }
    public void setAnomalyScore(double anomalyScore) { this.anomalyScore = anomalyScore; }
    public double getRiskScore() { return riskScore; }
    public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
    public boolean isAnomaly() { return isAnomaly; }
    public void setAnomaly(boolean isAnomaly) { this.isAnomaly = isAnomaly; }
    public String getRiskClass() { return riskClass; }
    public void setRiskClass(String riskClass) { this.riskClass = riskClass; }
    public List<String> getContributingFactors() { return contributingFactors; }
    public void setContributingFactors(List<String> contributingFactors) { this.contributingFactors = contributingFactors; }
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
}