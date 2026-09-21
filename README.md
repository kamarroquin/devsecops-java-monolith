# DevSecOps Java Monolith

Proyecto realizado como práctica final del curso de DevSecOps.

La aplicación es un sistema monolítico desarrollado con Java, Spring Boot y Maven. Permite realizar operaciones básicas de gestión de empleados utilizando una base de datos H2.

## Tecnologías utilizadas

* Java 17
* Spring Boot
* Maven
* JUnit
* Mockito
* JaCoCo
* Jenkins
* SonarQube
* JFrog Artifactory
* Docker
* Docker Compose

## Pipeline

El archivo `Jenkinsfile` ubicado en la raíz del proyecto contiene el pipeline utilizado para automatizar el proceso.

El pipeline realiza las siguientes etapas:

1. **Build**
   Compila el proyecto utilizando Maven.

2. **Testing**
   Ejecuta las pruebas unitarias desarrolladas con JUnit y Mockito.

3. **JaCoCo**
   Genera el reporte de cobertura de código.

4. **SonarQube**
   Analiza la calidad del código y posibles problemas.

5. **Package**
   Genera el archivo `.jar` de la aplicación.

6. **Artifactory**
   El artefacto generado es enviado a JFrog Artifactory.

El flujo general es:

```text
GitHub
   |
   v
Jenkins
   |
   +-- Build
   |
   +-- Testing + JaCoCo
   |
   +-- SonarQube
   |
   +-- Package
   |
   +-- Artifactory
```

## Docker

Para realizar las pruebas del pipeline utilicé Docker de forma local.

Se utilizaron contenedores para:

* Jenkins
* SonarQube
* PostgreSQL
* JFrog Artifactory

El archivo utilizado para levantar estos servicios se encuentra en:

```text
/resources/docker-compose.yml
```

Para levantar el ambiente se puede utilizar:

```bash
docker compose -f resources/docker-compose.yml up -d
```

## Ejecución del proyecto

Para ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

La aplicación puede visualizarse en:

```text
http://localhost:8080/empleados
```

Para ejecutar las pruebas:

```bash
./mvnw clean test
```

Para generar el artefacto:

```bash
./mvnw clean package
```

El archivo `.jar` generado queda dentro de la carpeta:

```text
target/
```

Este proyecto fue realizado con fines de aprendizaje para practicar un flujo básico de Integración Continua y Entrega Continua utilizando herramientas DevSecOps.
