# 💪 PyroFlex Gym

A gym management platform built with Spring Boot microservices and React Native mobile app.

---

## 📱 What It Does

- Member workout tracking and session logging
- Class booking and slot management
- Membership and subscription billing
- Push notifications and reminders
- QR code gym check-in
- Trainer and admin portals

---

## 🏗️ Architecture
Mobile App (React Native)
↓
API Gateway          ← single entry point, JWT auth
↓
┌──────────────────────────────────────┐
│ user  │ workout │ booking │ payment  │  ← Spring Boot services
│  svc  │   svc   │   svc   │   svc   │
└──────────────────────────────────────┘
↓
Apache Kafka         ← async events between services
↓
┌──────────────────────────────────────┐
│ MySQL │ MongoDB │  MySQL  │  MySQL   │  ← one DB per service
└──────────────────────────────────────┘

---

## 🧩 Services

| Service | Port | Purpose |
|---------|------|---------|
| `api-gateway` | 8080 | Routing, auth, rate limiting |
| `user-service` | 8081 | Members, trainers, authentication |
| `workout-service` | 8082 | Workout plans and session logs |
| `booking-service` | 8083 | Classes, slots, check-ins |
| `payment-service` | 8084 | Subscriptions and billing |
| `notification-service` | 8085 | Push, email, in-app alerts |
| `common-lib` | — | Shared DTOs, utilities, exceptions |

---

## 🛠️ Tech Stack

**Backend**
- Java 17, Spring Boot 3.2
- Spring Cloud Gateway + Eureka
- Apache Kafka
- MySQL, MongoDB, Redis
- JWT, Stripe (payments)

**Mobile**
- React Native + Expo (Android & iOS)
- TypeScript, Redux Toolkit

**DevOps**
- Docker + Docker Compose
- GitHub Actions (CI/CD)
- AWS (deployment)

---

## 📁 Project Structure
pyroflex-gym/
├── pom.xml                  # parent POM
├── docker-compose.yml       # local dev environment
├── contracts/               # OpenAPI specs
├── common-lib/              # shared library
├── api-gateway/
├── user-service/
├── workout-service/
├── booking-service/
├── payment-service/
├── notification-service/
└── mobile-app/              # React Native
---

## 🌿 Git Workflow
main        ← production, protected
└── develop ← default, all PRs merge here
└── feature/service-name-description

- Never push directly to `main` or `develop`
- Open PR → teammate reviews → merge to `develop`

---

## 🚀 Run Locally

```bash
# 1. Start databases and Kafka
docker-compose up -d

# 2. Build all modules
mvn clean install

# 3. Run a service
mvn -pl user-service spring-boot:run

# 4. Run mobile app
cd mobile-app && npx expo start
```

---

## ✅ Roadmap

- [x] Repo setup
- [ ] Common lib
- [ ] API Gateway
- [ ] User Service
- [ ] Workout Service
- [ ] Booking Service
- [ ] Payment Service
- [ ] Notification Service
- [ ] Mobile App
- [ ] CI/CD Pipelines
- [ ] AWS Deployment