# BobEsponjaU4


## Contenido
- [Introducción](#introducción)
- [Diagrama ER](#diagrama-entidad-relación)
- [Tecnologías](#tecnologías)
- [Estructura de Paquetes](#estructura-de-paquetes)
- [Configuración](#configuración)
- [Video](#video-tutorial)
- [Uso](#uso)
- [Rutas Principales](#rutas-principales)
- [Pruebas](#pruebas)
- [Mejoras Futuras](#mejoras-futuras)
- [Conclusión](#conclusión)

## Introducción

Este proyecto gestiona una base de datos inspirada en el universo de **Bob Esponja** (Fondo de Bikini) mediante una aplicación web construida con **Spring Boot**, **Thymeleaf** y **JPA/Hibernate**. Permite:

- CRUD de **Películas**, **Personajes** y **Lugares de Trabajo**
- Gestión de la relación muchos-a-muchos entre Películas y Personajes
- Asociación de cada Personaje a un Lugar de Trabajo
- Navegación y formularios interactivos con Thymeleaf  
---

## Diagrama Entidad-Relación

![Diagrama ER](src/main/resources/static/Diagrama.png)

---

## Tecnologías

- Java 17
- Spring Boot 3.5.0
- Spring Data JPA (Hibernate)
- Thymeleaf
- H2 (memoria) / JDBC
- Maven
- JUnit5, Mockito, AssertJ (tests)  

---

## Estructura de Paquetes
```

edu.badpals.bobesponjau4
├── controller # Spring MVC controllers
├── model # JPA entities: Movie, Personaje, Workplace
├── repository # Spring Data JPA repositories
├── service # Lógica de negocio y transacciones
├── resources
│ ├── static # CSS, imágenes y recursos
│ └── templates # Vistas Thymeleaf
└── test # Pruebas unitarias con JUnit5/Mockito

```
---

## Configuración

```yaml
# src/main/resources/application.yml
spring:
  datasource:
    url: jdbc:h2:mem:bobesponja;DB_CLOSE_DELAY=-1
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---


## Video Tutorial

Puedes ver un recorrido por la aplicación en este video:

[![Video Tutorial](https://img.youtube.com/vi/ZN1J4LJnlZU/0.jpg)](https://youtu.be/ZN1J4LJnlZU)

O directamente en YouTube:  
https://youtu.be/ZN1J4LJnlZU

## Uso

1. Clonar repositorio y abrir con IntelliJ o VSCode
2. Ejecutar:
   ```bash
   mvn spring-boot:run
   ```
3. Acceder a http://localhost:8080/menu

4. Navegar entre:

- Películas (`/movies/list`)
- Personajes (`/personajes/list`)
- Lugares de Trabajo (`/workplaces/list`)

---

## Rutas Principales

| Sección         | URL                     | Operaciones                                          |
|-----------------|-------------------------|------------------------------------------------------|
| Menú Principal  | `/menu`                 | Ver menú                                             |
| Películas       | `/movies/list`          | Listar, Añadir, Editar, Eliminar, Gestionar relación |
| Personajes      | `/personajes/list`      | Listar, Añadir, Editar, Eliminar                     |
| Workplaces      | `/workplaces/list`      | Listar, Añadir, Editar, Eliminar                     |


## Pruebas

- Ejecutar todas con:
  ```bash
  mvn test
  ```

- Pruebas unitarias en `src/test/java/edu/badpals/bobesponjau4/service`

---

## Mejoras Futuras

- Búsqueda y filtros avanzados
- Paginación de listados
- API REST pública
- Exportar informes en PDF/CSV
- Internacionalización

---

## Conclusión

“BobEsponjaU4” demuestra cómo construir una aplicación CRUD con **Spring Boot**, separando capas de modelo, repositorio, servicio y controlador, y usando Thymeleaf para la capa de presentación. Es fácilmente extensible y sirve como base para proyectos más complejos.

  
