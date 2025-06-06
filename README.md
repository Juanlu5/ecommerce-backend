# 🛒 E-commerce Backend API (Spring Boot + Docker)

Este proyecto es una **API REST** para un sistema de e-commerce desarrollada en **Java con Spring Boot**. Permite gestionar productos a través de operaciones CRUD y está preparada para entornos de producción, incluyendo pruebas, documentación y contenedores Docker.

---

## Tecnologías utilizadas

-  **Java 21**
-  **Spring Boot 3.5**
-  **Spring Data JPA**
-  **Docker + Docker Compose**
-  **MySQL 8**
-  **JUnit + Mockito**
-  **Swagger (OpenAPI)**
-  **MapStruct** (DTO mapping)
-  **Bean Validation**
-  **H2** (base de datos para tests)

---

## Características del proyecto

- **CRUD completo** para la gestión de productos.
- **Arquitectura en capas**: Controller, Service, Repository, DTOs.
- **Mapeo automático** con MapStruct para DTOs.
- **Validaciones globales** con mensajes claros.
- **Gestión centralizada de errores**.
- **Tests unitarios y de integración** con JUnit y Mockito.
- **Contenerización** con Docker y MySQL.
- **Documentación interactiva** con Swagger UI.

---

## Cómo levantar el proyecto con Docker

### 1. Compilar el proyecto
```bash
./mvnw clean package
```

### 2. Levantar contenedores (API + Base de datos)
```bash
docker compose up --build
```

### Acceder a Swagger UI
Accede a la documentación interactiva en:
```
http://localhost:8080/swagger-ui.html
```

---

## Cómo ejecutar los tests
```bash
./mvnw test
```

---

## Estructura del proyecto
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.juanlu.ecommerce
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── exception
│   │   │       ├── mapper
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       └── service
│   └── test
│       └── java
│           └── com.juanlu.ecommerce
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── README.md
└── .gitignore
```

---

## Autor
**Juan Luis**  
Desarrollador backend con enfoque en buenas prácticas, pruebas y despliegue profesional.

🔗 [LinkedIn](#) *(www.linkedin.com/in/juan-luis-romero-algaba-488546330)*  
📧 [Email](#) *(juanluu.ra@gmail.com)*  
