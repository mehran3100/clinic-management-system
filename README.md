# ⚔️ Clinic Management System — Microservices Edition (KRATOS STYLE)

> “Boy... we're building something worthy of the gods.” – Kratos

---

## 🩺 Project Overview
The **Clinic Management System** is a modular, battle-hardened microservices project forged in Java 17 and Spring Boot.  
It simulates a healthcare backend worthy of the Nine Realms, featuring robust patient record handling, secure communication, and scalable service discovery.

---

## 🛠️ Technologies Wielded
- ☕ Java 17
- 🌱 Spring Boot
- 🌩️ Spring Cloud (Eureka, Gateway)
- 🛡️ Keycloak (OAuth2 Authorization)
- 🐘 PostgreSQL
- 🐳 Docker (used for full container orchestration 💪)
- 🧬 HAPI FHIR (future realm — healthcare interoperability)

---

## 🧩 Microservices Realms

| Service              | Description                                           |
|----------------------|-------------------------------------------------------|
| 🧭 **Discovery Server**    | Registry where all realms announce themselves        |
| 🛡️ **API Gateway**         | Entry point to the Nine Realms — routes & protects   |
| 🩺 **Patient Service**     | Holds the scrolls of the wounded and the healed      |
| 📅 **Appointment Service** | Guides the fates of doctor-patient encounters       |
| 📦 **(Planned) FHIR Service** | HL7 FHIR for healthcare data standards compliance |

---

## ✅ Features Worthy of Olympus
- Secure RESTful APIs via Keycloak 🔐
- Microservice registration & discovery via Eureka 🌍
- Pagination support with clean Swagger UI 🧼
- Global exception handling for misbehaving mortals ⚠️
- Custom ASCII banners for every service 💀🔥
- Clean code, SOLID principles, and future-ready architecture

---

## 🧙‍♂️ Banners of the Realms (Each service logs its own spirit)

| Service              | Banner Message                          |
|----------------------|------------------------------------------|
| 🧭 Discovery Server  | “Boy... all realms must be found.”       |
| 🛡️ API Gateway       | “No request passes without my blessing.” |
| 🩺 Patient Service   | “Someone needs healing.”                 |
| 📅 Appointment       | “We do not miss our fate.”               |

---

## 🚀 How to Summon the System
```bash
1. Start the Discovery Server
2. Start the API Gateway
3. Start Patient Service and Appointment Service
4. Visit Swagger UI or Postman to begin invoking endpoints
5. Authenticate using Keycloak if required (tokens, boy!)
```

---

## 📚 API Documentation
Now powered by Swagger UI — with clean pagination & sorting!
No more `["string"]` nonsense. Only structured query parameters like:
```
?page=0&size=10&sort=id,asc
```

---

## ⚔️ Future Enhancements
- Notification Service via Kafka
- FHIR integration for full clinical data standards
- Frontend portal with role-based UI

---

## 📬 Contact the Architect
**Created by Mehran Zare**  
GitHub: [mehran3100](https://github.com/mehran3100)  
📧 mehran.zare3100@gmail.com
