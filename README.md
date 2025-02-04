# 🚀 API Factus - Backend (Spring Boot)

Este es el backend de **API Factus**, desarrollado con **Spring Boot** y desplegado en **Render** para pruebas.

## 📌  Descripción

Este backend proporciona una API REST para integración del sistema de Factus, permitiendo crear y leer facturas en un entorno seguro y escalable.

🌍 **URL del backend en Render:** [apifactus-spring.onrender.com](https://apifactus-spring.onrender.com)

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security** 
- **Spring Data JPA**
- **PostgreSQL**
- **Docker**

## 📌 Instalación y Ejecución Local

### 1️⃣ **Clonar el repositorio**
```sh
 git clone https://github.com/felix73sanchez/apifactus-spring.git
 cd apifactus-backend
```

### 2️⃣ **Compilar y ejecutar la aplicación**
```sh
mvn clean package
java -jar target/apifactus.jar
```

La API estará disponible en `http://localhost:1221`

## 📌 Endpoints Principales

📌 **Autenticación**
```http
GET /v1/auth/token
```

📌 **Facturas**
```http
GET /v1/facturas
GET /v1/facturas/{id}
POST /guardar
```

## 🚀 Despliegue en Render

El backend está desplegado en **Render**, lo que permite realizar pruebas en la siguiente URL:

🔗 **API Base URL:** `https://apifactus-spring.onrender.com`

Ejemplo de consulta:
```sh
curl -X GET https://apifactus-spring.onrender.com/v1/facturas
```

## 📌 Contribuciones
¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto, crea un **issue** o envía un **pull request**.

## 📜 Licencia
Este proyecto está bajo la licencia **MIT**.

