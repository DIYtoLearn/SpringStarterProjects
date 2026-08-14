# Learning State

## Current Project

URL Shortener

## Learning Philosophy

- Understand before copying.
- Implement features myself whenever possible.
- Use AI primarily as a tutor and reviewer.
- Understand why Spring behaves the way it does.
- Track recurring mistakes and knowledge gaps.

## Java

### Current Level

Strong basic Java foundation.

### Concepts To Strengthen

- Generics
- Collections
- Exceptions
- Streams
- Object-oriented design
- Testing

## Spring Boot

### Current Level

Beginner.

### Concepts To Learn

- Spring Boot application structure
- IoC
- Dependency Injection
- Beans
- Component scanning
- Configuration
- REST controllers
- Request/response handling
- Validation
- Service layer
- Repository layer
- Spring Data JPA
- Transactions
- Testing

## Knowledge Levels

0 = Never encountered
1 = Heard of it
2 = Can explain with help
3 = Can explain independently
4 = Can implement with guidance
5 = Can implement independently
6 = Can debug and explain edge cases
7 = Can teach it

## Current Knowledge

| Concept | Level | Notes |
|---|---:|---|
| Spring Boot | 1 | Initial project structure, startup class, configuration, and context test introduced; explanation not yet assessed. |
| IoC | 0 | |
| Dependency Injection | 0 | |
| Beans | 0 | |
| REST Controllers | 0 | |
| Validation | 0 | |
| Spring Data JPA | 0 | |
| Transactions | 0 | |

## Recurring Mistakes

- Explain the startup path from `main()` and `SpringApplication.run(...)` to the embedded web server.
- Distinguish Maven's production dependency from its test dependency.

## Concepts To Revisit

None yet.

## Current Objective

Create the base Spring Boot project and understand its structure
before implementing application features.

## Session Notes — 2026-08-14

- Reviewed the generated project structure and the roles of `pom.xml`,
  `src/main`, `src/test`, `application.properties`, and the Maven Wrapper.
- Simplified the Phase 1 dependency set to Spring Web MVC plus its test
  support; database, validation, and Lombok dependencies were deferred.
- The Spring context test passed, but application startup and the embedded
  server have not yet been observed directly.
