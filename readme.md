# 🛒 E-Commerce Microservices Platform

A distributed e-commerce backend system built using **Microservices Architecture** with Spring Boot. This project demonstrates real-world backend design patterns including service discovery, API Gateway, and both synchronous and asynchronous inter-service communication.

---

## 🚀 Features

- Microservices-based architecture with independent services
- Service discovery using Eureka Server
- Centralized routing using API Gateway
- Asynchronous communication using RabbitMQ
- Synchronous communication using Feign Client
- Containerized services using Docker
- Separate databases for each service

---

## 🧩 Microservices Overview

| Service           | Description |
|------------------|------------|
| **Order Service**     | Handles order creation and management |
| **Product Service**   | Manages product details |
| **Inventory Service**| Maintains stock availability |
| **Rating Service**   | Handles product ratings and updates |
| **API Gateway**      | Routes requests to appropriate services |
| **Eureka Server**    | Service discovery and registration |

---

## 🔄 Inter-Service Communication

### 🔹 Synchronous Communication
- **Order ↔ Inventory**
- Implemented using **Feign Client**
- Ensures orders are placed only if inventory is available

### 🔹 Asynchronous Communication
- **Product ↔ Rating**
- Implemented using **RabbitMQ**
- Updates product average rating asynchronously

---

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot, Spring Cloud
- **Architecture:** Microservices
- **Service Discovery:** Eureka Server
- **API Gateway:** Spring Cloud Gateway
- **Messaging:** RabbitMQ
- **Communication:** Feign Client
- **Database:** MySQL / PostgreSQL
- **Containerization:** Docker
- **Build Tool:** Maven

---
