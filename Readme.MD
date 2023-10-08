# Spring Boot Rest API Boilerplate Template


This project is a template to create spring boot rest api app.

This is to quickly start api development with basic package structure and supports required.

  - Package structure definitions
  - Spring Data JPA Support
  - Spring Security Basic Auth Support
  - Logging support
  - Entity classes
  - Custom Object Mapper 
  - Custom Exception handler with controller advice
  - Utility class for error code handling.
  

## Build & Run

### Local

- Perform Maven Build

```sh
make build
```
### Docker

The below command will build a local docker image and run as a container
```sh
make run
```

### Kubernetes

If you've a Kubernetes context available via `docker-desktop` or any other method, below command will run a `Deployment` using resource definition available in `k8s` folder.
```sh
make run-k8s
```

