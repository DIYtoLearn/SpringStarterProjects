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
| Spring Boot | 2 | Can explain the basic startup path with help: `main()` calls `SpringApplication.run(...)`, which starts the Spring application and embedded server. |
| `@SpringBootApplication` | 2 | Understands it identifies the primary application configuration and enables component scanning and auto-configuration; needs reinforcement on the individual mechanisms. |
| Component scanning | 2 | Can explain that Spring scans the root package and its subpackages for Spring-annotated classes to manage. |
| Auto-configuration | 1 | Introduced using Spring Web and embedded Tomcat as the example; explanation not yet assessed. |
| IoC | 0 | |
| Dependency Injection | 0 | |
| Beans | 0 | |
| REST Controllers | 0 | |
| Validation | 0 | |
| Spring Data JPA | 0 | |
| Transactions | 0 | |

## Recurring Mistakes

- Distinguish Maven's production dependency from its test dependency.

## Concepts To Revisit

- Auto-configuration: explain how Spring Boot uses available dependencies and defaults, and how application configuration can override a default.

## Current Objective

Complete Phase 1 by understanding `application.properties` and reinforcing
auto-configuration before implementing application features.

## Session Notes — 2026-08-14

- Reviewed the generated project structure and the roles of `pom.xml`,
  `src/main`, `src/test`, `application.properties`, and the Maven Wrapper.
- Simplified the Phase 1 dependency set to Spring Web MVC plus its test
  support; database, validation, and Lombok dependencies were deferred.
- The Spring context test passed, but application startup and the embedded
  server have not yet been observed directly.

## Session Notes — 2026-08-15

- Started the application and identified the high-level difference between Maven
  compilation and Spring Boot startup.
- Explained the startup path: `main()` calls `SpringApplication.run(...)`, which
  creates the Spring application context and starts the embedded Tomcat server.
- Learned that `@SpringBootApplication` combines application configuration,
  component scanning, and auto-configuration.
- Correctly explained why the application class belongs in the root package:
  component scanning covers that package and its subpackages, so future
  `controller` and `service` packages will be discovered automatically.
- Official documentation was too dense at this stage; use short, plain-language
  explanations first, then refer back to documentation for confirmation.
- Introduced auto-configuration but did not yet assess an independent explanation.
