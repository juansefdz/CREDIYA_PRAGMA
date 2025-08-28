# MicroServicio Autenticación
## CrediYa – Plataforma de Solicitudes de Préstamos

CrediYa es una plataforma que busca digitalizar y optimizar la gestión de solicitudes de préstamos personales, eliminando procesos manuales y presenciales.
El sistema permite que los solicitantes ingresen sus datos y la información del préstamo que desean, los evalúa automáticamente y notifica a los administradores para la aprobación final.

## Funcionalidades principales

- 

## Arquitectura

La solución está construida bajo un modelo de microservicios reactivos con Spring WebFlux y arquitectura hexagonal.

### Microservicios principales:

- auth-service → autenticación y gestión de usuarios.

## Tecnologías:

- Backend: Java 17, Spring Boot, Spring WebFlux.

- DB Relacional (RDS - PostgreSQL): Solicitudes, usuarios y préstamos.

## Estructura del proyecto

Cada microservicio se encuentra en un repositorio independiente siguiendo el scaffold de Clean Architecture Bancolombia.

/crediya
   ├── auth-service


Dentro de cada microservicio:

/src
   ├── main/java/com/crediya
   │     ├── application   -> Casos de uso
   │     ├── domain        -> Entidades del dominio
   │     ├── infrastructure-> Adapters (DB, SQS, REST)
   │     └── entrypoints   -> Controladores WebFlux
   └── test/java/com/crediya 

## Instalación y ejecución local

- Clonar el repositorio correspondiente:

        git clone https://github.com/crediya/crediya-auth-microservice.git
        cd loan-service


- Compilar y correr con Gradle:

        ./gradlew bootRun


La API estará disponible en:

    http://localhost:8080


Documentación de endpoints:

`http://localhost:8080/swagger-ui.html`

## Testing

Ejecutar pruebas unitarias:

    ./gradlew test
