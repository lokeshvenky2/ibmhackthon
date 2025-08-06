import numpy as np
from sklearn.ensemble import IsolationForest, RandomForestClassifier
from sklearn.preprocessing import StandardScaler
import joblib

# Generate synthetic data (e.g., features like login attempts, data transfer, access times)
def generate_synthetic_data(n_samples=1000):
    np.random.seed(42)
    X = np.random.rand(n_samples, 5)  # 5 features: login attempts, data transfer, etc.
    # Simulate anomalies by adding outliers
    X[:100, :] = X[:100, :] + np.random.uniform(5, 10, size=(100, 5))
    return X

# Train Isolation Forest for anomaly detection
def train_isolation_forest(X):
    iso_forest = IsolationForest(contamination=0.1, random_state=42)
    iso_forest.fit(X)
    anomaly_scores = -iso_forest.score_samples(X)  # Higher score = more anomalous
    return iso_forest, anomaly_scores

# Train Random Forest for risk classification
def train_random_forest(X):
    # Create synthetic labels (0 = low risk, 1 = medium risk, 2 = high risk)
    y = np.zeros(len(X))
    y[X[:, 0] > 0.8] = 1  # High login attempts
    y[X[:, 1] > 0.7] = 2  # High data transfer
    rf_classifier = RandomForestClassifier(n_estimators=100, random_state=42)
    rf_classifier.fit(X, y)
    risk_scores = rf_classifier.predict_proba(X)[:, 2] * 50  # Scale to 0-50
    return rf_classifier, risk_scores

# Main function to demonstrate the process
def demonstrate_threat_detection():
    print("Demonstrating Threat Detection using Isolation Forest and Random Forest...")
    
    # Generate and scale data
    X = generate_synthetic_data()
    scaler = StandardScaler()
    X_scaled = scaler.fit_transform(X)
    
    # Train models
    iso_forest, anomaly_scores = train_isolation_forest(X_scaled)
    rf_classifier, risk_scores = train_random_forest(X_scaled)
    
    # Combine results
    results = {
        "ipAddress": [f"192.168.{i}.1" for i in range(len(X))],
        "anomalyScore": anomaly_scores,
        "riskScore": risk_scores,
        "isAnomaly": anomaly_scores > np.percentile(anomaly_scores, 90),
        "riskClass": np.where(risk_scores > 40, "High", np.where(risk_scores > 25, "Medium", "Low"))
    }
    
    print("Sample Results:")
    for i in range(5):  # Show first 5 results
        print(f"IP: {results['ipAddress'][i]}, Anomaly Score: {results['anomalyScore'][i]:.2f}, "
              f"Risk Score: {results['riskScore'][i]:.2f}, Is Anomaly: {results['isAnomaly'][i]}, "
              f"Risk Class: {results['riskClass'][i]}")
    
    # Save models for demonstration (optional)
    joblib.dump(iso_forest, 'isolation_forest_model.joblib')
    joblib.dump(rf_classifier, 'random_forest_model.joblib')
    print("Models saved as 'isolation_forest_model.joblib' and 'random_forest_model.joblib'.")

if __name__ == "__main__":
    demonstrate_threat_detection()