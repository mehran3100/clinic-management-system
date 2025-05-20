Clinic Management System - Microservices Architecture

🩺 Project Overview
The Clinic Management System is a modular microservices-based project developed using Java 17 and Spring Boot. It is designed to simulate the core backend services of a healthcare management platform, including patient record handling, service registration, and discovery.

🛠️ Technologies Used
- Java 17
- Spring Boot
- Spring Cloud (Eureka, Config, Gateway)
- PostgreSQL
- Docker (future enhancement)
- HAPI FHIR (for healthcare data interoperability, future enhancement)
  
🧩 Microservices Structure
The application consists of the following services:
- **Eureka Discovery Server**: Service registry for discovering microservices.
- **API Gateway**: Central gateway for routing client requests to appropriate services.
- **Patient Service**: Manages patient records and operations (CRUD).
- (Planned) **FHIR Service**: For supporting HL7 FHIR standards.
  
✅ Features
- RESTful APIs for patient operations
- Secure endpoints with Keycloak integration
- Service registration and discovery with Eureka
- Easy extensibility for other clinical modules
  
🚀 Running the Project
1. Start the Eureka Discovery Server.
2. Start the API Gateway.
3. Start the Patient Service.
4. Access the API via Postman or browser.
5. Authenticate using Keycloak if security is enabled.
   
📬 Contact
Created by Mehran Zare
GitHub: https://github.com/mehran3100
Email: mehran.zare3100@gmail.com
