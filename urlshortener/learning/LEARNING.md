# Learning State

## Current Project

URL Shortener

## Learning Philosophy

- Understand before copying.
- Implement features myself whenever possible.
- Use AI primarily as a tutor and reviewer.
- Understand why Spring behaves the way it does.
- Track recurring mistakes and knowledge gaps.
- When introducing a Spring Boot exercise, provide a small syntax skeleton and
  explain its parts first. Java fundamentals are familiar, but Spring Boot
  annotation and API syntax is still new.
- The tutor may inspect and review repository code whenever it is useful for
  teaching, without waiting for the learner to paste it into the chat.

### Persistent Collaboration Preferences

- Preserve the Spring syntax-skeleton preference and the permission to inspect
  code whenever `learning/LEARNING.md` is updated in future sessions.
- Treat these preferences as durable learning context, not temporary session
  notes; do not remove or overwrite them unless the learner explicitly changes
  them.

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
| Auto-configuration | 2 | Can explain with help that Spring Boot evaluates available dependencies and applies matching defaults, such as a web server for Spring Web MVC. |
| External configuration | 3 | Changed `server.port` in `application.properties` to `1999` and verified that embedded Tomcat used it at startup. |
| IoC | 0 | |
| Dependency Injection | 0 | |
| Beans | 0 | |
| REST Controllers | 4 | Implemented a `@RestController` with two `@GetMapping` methods using a syntax skeleton; understands that the annotation marks the class, not a URL path. |
| Request mapping | 3 | Can explain that Spring routes requests by HTTP method plus path, and observed the startup error caused by two `GET /` mappings. |
| Validation | 0 | |
| Spring Data JPA | 0 | |
| Transactions | 0 | |

## Recurring Mistakes

- Distinguish Maven's production dependency from its test dependency.
- Use meaningful, conventional Java method names; method names begin with a
  lowercase letter and should describe their purpose (for example, `getStatus`).

## Concepts To Revisit

- Auto-configuration: explain independently how Spring Boot uses available
  dependencies and defaults, and how application configuration overrides them.
- Request mapping: distinguish the class-level shared path provided by
  `@RequestMapping` from an individual method mapping such as `@GetMapping`.

## Current Objective

Continue Phase 2 by learning class-level `@RequestMapping`, then explain how
Spring combines it with a method-level `@GetMapping` to form an endpoint path.

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

## Session Notes — 2026-08-15 (Configuration and First Controller)

- Explained that Spring Web MVC on the classpath satisfies auto-configuration
  conditions for a web application; clarified the difference between
  component scanning and auto-configuration.
- Used `application.properties` to override `server.port` from `8080` to
  `1999`, then verified that the application ran at `http://localhost:1999/`.
- Correctly identified that a `404` at `/` initially meant the server was
  running but no handler existed for that request.
- Created `HomeController` in the scanned `controller` package with
  `@RestController`, `@GetMapping("/")`, and a `String` response.
- Added a separate `GET /status` handler and learned that Spring selects a
  handler using the HTTP method plus path.
- Deliberately created two `GET /` mappings, read the resulting
  `Ambiguous mapping` startup error, and restored the distinct mappings.
- Prefers small Spring syntax skeletons and explanations before exercises;
  the tutor may inspect repository code whenever it helps teaching. These are
  durable preferences and must be preserved in future learning-state updates.
