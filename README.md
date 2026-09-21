# DevSecOps Java Monolith

Este repositorio contiene una aplicación monolítica desarrollada en Java con Spring Boot, utilizada como proyecto práctico para demostrar la implementación de un proceso de Integración Continua y Entrega Continua utilizando Jenkins.


## Descripción de la aplicación

La aplicación consiste en un sistema sencillo de gestión de empleados desarrollado utilizando una arquitectura monolítica.

Desde la aplicación es posible registrar, consultar, modificar y eliminar empleados.

Cada empleado contiene información como:

* Nombre.
* Apellido.
* Correo electrónico.
* Puesto.
* Salario.

La aplicación utiliza Spring Boot como framework principal, Thymeleaf para la visualización de las páginas y Spring Data JPA para el acceso a datos.

Para facilitar la ejecución del proyecto se utiliza una base de datos H2 en memoria, evitando la necesidad de instalar o configurar un servidor de base de datos adicional.

## Tecnologías utilizadas

El proyecto utiliza principalmente las siguientes tecnologías:

* Java 17.
* Spring Boot.
* Maven.
* Spring MVC.
* Spring Data JPA.
* Thymeleaf.
* H2 Database.
* JUnit 5.
* Mockito.
* JaCoCo.
* Jenkins.
* SonarQube.
* JFrog Artifactory.
* Docker.
* Docker Compose.
* Git.
* GitHub.

## Estructura general del proyecto

La aplicación sigue una estructura tradicional por capas:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
H2 Database
```

El controlador recibe las solicitudes provenientes de la aplicación web.

La capa de servicio contiene la lógica de negocio y funciona como intermediaria entre el controlador y el acceso a datos.

La capa Repository utiliza Spring Data JPA para realizar las operaciones necesarias sobre la base de datos H2.

Todo el sistema se encuentra contenido dentro de una misma aplicación, por lo que corresponde a una arquitectura monolítica.

## Pipeline CI/CD

En la raíz del repositorio se encuentra el archivo:

```text
Jenkinsfile
```

Este archivo contiene la definición del pipeline utilizado por Jenkins para automatizar la construcción, pruebas, análisis y generación del artefacto de la aplicación.

El flujo implementado es el siguiente:

```text
GitHub
   |
   v
Jenkins
   |
   +---- Build
   |
   +---- Testing
   |       |
   |       +---- JUnit
   |       |
   |       +---- JaCoCo
   |
   +---- SonarQube
   |
   +---- Package
   |
   +---- Verify Artifact
   |
   +---- Artifactory
```

## Stage Build

El primer stage del pipeline se encarga de compilar el proyecto.

Jenkins utiliza Maven Wrapper, incluido dentro del repositorio, por medio del siguiente comando:

```bash
./mvnw clean compile
```

El uso de Maven Wrapper permite utilizar la versión de Maven definida para el proyecto sin depe

