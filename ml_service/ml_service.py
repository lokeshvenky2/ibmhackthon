from flask import Flask, jsonify
from flask_cors import CORS
import random
import time

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Sample IP addresses and contributing factors
ip_addresses = [
    "192.168.1.1", "10.0.0.5", "172.16.254.1", "192.168.0.100",
    "10.1.1.10", "172.16.0.1", "192.168.1.200", "10.0.0.100"
]
contributing_factors = [
    "Unusual login attempts", "High data transfer", "Suspicious network activity",
    "Multiple failed logins", "Access outside normal hours"
]
recommendations = [
    "Investigate immediately", "Monitor closely", "Restrict access",
    "Review logs", "Block IP"
]

# Mock threat prediction function
def predict_threats():
    threats = []
    for ip in ip_addresses:
        risk_score = random.uniform(10, 50)
        is_anomaly = random.random() > 0.3
        risk_class = "High" if risk_score > 40 else "Medium" if risk_score > 25 else "Low"
        factors = random.sample(contributing_factors, k=random.randint(1, 3))
        recommendation = random.choice(recommendations)
        
        threat = {
            "ipAddress": ip,
            "riskScore": round(risk_score, 2),
            "isAnomaly": is_anomaly,
            "riskClass": risk_class,
            "contributingFactors": factors,
            "recommendation": recommendation
        }
        threats.append(threat)
    return threats

# API endpoint
@app.route('/api/predict-threats', methods=['GET'])
def get_threats():
    threats = predict_threats()
    time.sleep(1)  # Simulate processing delay
    return jsonify(threats)

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)