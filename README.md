# API de socios y préstamos (biblioteca)

API REST para gestionar los **socios** de una biblioteca y los **préstamos** de libros.
Parcial de promoción de la Tecnicatura en Desarrollo de Software (Instituto Superior ICOP).

## Stack

Java 25 · Spring Boot 4 · Spring Web MVC · Spring Data JPA · Flyway · H2

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/socios` | Listar socios |
| `GET` | `/socios/{id}` | Obtener un socio |
| `POST` | `/socios` | Crear un socio |
| `DELETE` | `/socios/{id}` | Eliminar un socio |
| `POST` | `/prestamos` | Registrar un préstamo |
| `GET` | `/prestamos/socio/{id}` | Préstamos de un socio |

## Decisiones

- **Arquitectura en capas:** controladores, servicios (interfaz + implementación), repositorios y entidades.
- **DTOs de entrada y salida** separados de las entidades.
- **Reglas de negocio** con excepciones propias: no se presta un libro que ya está prestado y no se repite el DNI de un socio.
- **Manejo global de errores** (`@RestControllerAdvice`) con respuestas claras.
- **Migraciones con Flyway** (`db/migration`): el esquema y los datos iniciales se versionan con el código.
- **Configuración por variables de entorno**: la conexión a la base se lee de un archivo `.env` que no se sube al repositorio.

## Cómo ejecutarlo

1. Cloná el repositorio.
2. Copiá `.env.example` como `.env`.
3. Ejecutá:

```bash
./mvnw spring-boot:run
```

La API queda en `http://localhost:8080`.

## Autor

Agustín Rosso · [github.com/arosso-arch](https://github.com/arosso-arch)
