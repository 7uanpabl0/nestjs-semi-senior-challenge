# 📨 Notificación Service

Microservicio de eventos de notificaciones (logins, transacciones, etc.) usando **Spring Boot**, **Kafka**, **PostgreSQL**, y **Docker**. Recibe eventos, los procesa, los guarda en base de datos y expone endpoints de consulta y simulación.

---

## 🚀 Tecnologías

- Spring Boot 3+
- Apache Kafka (eventos)
- PostgreSQL (persistencia)
- Docker + Docker Compose
- Hibernate / JPA
- UUID + JSON events
- SLF4J Logs

---


## 🐳 Despliegue con Docker

### 🔨 1. Compilar el proyecto

```bash
./mvnw clean package -DskipTests
Genera el JAR en target/notificacion-service-0.0.1-SNAPSHOT.jar


## SQL
ejecutar el archivo schema.sql para la base de datos

🔼 3. Levantar todos los servicios
bash
Copiar
Editar
docker-compose up --build


✅ Requisitos
Docker + Docker Compose

JDK 17

Maven (opcional si usas mvnw)

Puerto 5432 libre (Postgres)

Puerto 9092 libre (Kafka)

✨ Autor
Desarrollado por Juan Pablo Muñoz – Ingeniero de Software
🔗 github.com/7uanpabl0

