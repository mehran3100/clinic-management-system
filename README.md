
# ⚔️ Clinic Management System — Microservices Edition (KRATOS STYLE)

> “Boy... we're building something worthy of the gods.” – Kratos

---

## 🩺 Project Overview
The **Clinic Management System** is a modular, battle-hardened microservices project forged in Java 17 and Spring Boot.  
It simulates a healthcare backend worthy of the Nine Realms, featuring robust patient record handling, secure communication, event-driven messaging, and scalable service discovery.

---

## 🛠️ Technologies Wielded
- ☕ Java 17
- 🌱 Spring Boot
- 🌩️ Spring Cloud (Eureka, Gateway)
- 🛡️ Keycloak (OAuth2 Authorization)
- 🐘 PostgreSQL
- 🐳 Docker (used for full container orchestration 💪)
- 📡 Apache Kafka (event-driven architecture 🧠⚡)
- 🧬 HAPI FHIR (future realm — healthcare interoperability)

---

## 🧩 Microservices Realms

| Service                    | Description                                                |
|----------------------------|------------------------------------------------------------|
| 🧭 **Discovery Server**         | Registry where all realms announce themselves             |
| 🛡️ **API Gateway**              | Entry point to the Nine Realms — routes & protects        |
| 🩺 **Patient Service**          | Holds the scrolls of the wounded and the healed           |
| 📅 **Appointment Service**      | Guides the fates of doctor-patient encounters             |
| 📨 **(Planned) Notification**   | Sends ravens (SMS/email) upon patient events              |
| 📈 **(Planned) Analytics**      | Records all events in the Book of Eternity                |
| 📦 **(Planned) FHIR Service**   | HL7 FHIR for healthcare data standards compliance         |

---

## ✅ Features Worthy of Olympus
- Secure RESTful APIs via Keycloak 🔐
- Kafka-powered event-driven microservices ⚡
- Microservice registration & discovery via Eureka 🌍
- Auto-appointment scheduling on patient creation 📅
- Event-based notification trigger support 📩
- Global exception handling for misbehaving mortals ⚠️
- Pagination with Swagger UI 🧼
- Custom ASCII banners for each service 💀🔥
- Clean code, SOLID principles, and future-ready architecture

---

## 📡 Kafka: Voice of the Gods

When a patient is created:
- 🛠️ `patient-service` sends a `PatientCreatedEvent` to the `patient-created` topic
- 📅 `appointment-service` listens and:
  - Stores the patient locally
  - Schedules their first consultation
- 📨 `notification-service` (planned) sends welcome messages
- 📈 `analytics-service` (planned) logs it for Valhalla's dashboards

> “One event. Infinite reactions.”

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
docker-compose up --build
```

Then open:
- 🔗 Eureka: http://localhost:8761
- 🔗 Gateway: http://localhost:8083/api/patients

Use Swagger or Postman to create a patient and trigger the event-based prophecy.

---

## 📚 API Documentation
Powered by Swagger UI — with clean pagination & sorting:
```
?page=0&size=10&sort=id,asc
```

---

## ⚔️ Future Enhancements
- Kafka-powered notification-service (email/SMS)
- Kafka-based analytics/audit logs
- Dead-letter queue support for failed events
- Real-time dashboard for event monitoring
- Full HL7 FHIR integration

---

## 📬 Contact the Architect
**Created by Mehran Zare**  
GitHub: [mehran3100](https://github.com/mehran3100)  
📧 mehran.zare3100@gmail.com

🔗 LinkedIn: [mehran-zare](https://www.linkedin.com/in/mehran-zare/)
