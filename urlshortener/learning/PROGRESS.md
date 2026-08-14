# Learning Progress

## 2026-08-14

### Project Setup

- Created URL Shortener Spring Boot project.
- Created AI tutor documentation.
- Initialized Codex.
- Established repository learning files.

### Current Status

Project structure created.

Application implementation has not started.

### Next Step

Understand the generated Spring Boot project structure before writing
the first application feature.

### Phase 1 Session — Project Structure

- Reviewed the purpose of the generated Spring Boot files and directories.
- Reviewed Maven's role and the dependencies in `pom.xml`.
- Reduced the project to the Phase 1 dependency set: Spring Web MVC and
  web test support only.
- Ran `./mvnw test`; the generated `contextLoads()` test passed
  (1 test, 0 failures).

### Exact Stopping Point

The application was started manually. The next task is to explain
auto-configuration in the context of the Spring Web dependency, then inspect
and understand `application.properties`.

## 2026-08-15

### Phase 1 Session — Application Startup

- Ran the application and observed the embedded server startup.
- Distinguished Maven compilation from Spring Boot startup.
- Learned the purposes of `main()`, `SpringApplication.run(...)`, and
  `@SpringBootApplication` at a beginner level.
- Learned that component scanning begins in
  `com.DeatHertZ.urlshortener` and includes its subpackages.
- Began auto-configuration: Spring Boot uses available dependencies to apply
  defaults, such as starting embedded Tomcat when Spring Web is present.

### Concepts to Reinforce

- Auto-configuration versus manually supplied configuration.
- The role of `application.properties` in overriding defaults.

### Exact Next Learning Objective

In your own words, explain why Spring Web leads Spring Boot to configure a web
application, then inspect `application.properties` and identify how it can
override a default such as the server port.

### Phase 1 Completion and Phase 2 Start — Configuration and Routing

- Explained the relationship between the Spring Web MVC dependency,
  `@SpringBootApplication`, and web auto-configuration.
- Updated `application.properties` with `server.port=1999` and confirmed the
  embedded server listened on that port.
- Created `HomeController` in `com.DeatHertZ.urlshortener.controller`.
- Implemented `GET /` and `GET /status` using `@RestController` and
  `@GetMapping`, returning plain-text `String` response bodies.
- Triggered and interpreted an ambiguous mapping error by temporarily mapping
  both methods to `GET /`; restored the correct distinct paths afterward.

### Concepts Learned

- External configuration through `application.properties`.
- `@RestController` as a controller whose return values become response bodies.
- `@GetMapping` as a mapping from an HTTP GET request and URL path to a Java
  method.
- Spring MVC's startup-time validation of conflicting mappings.

### Concepts to Reinforce

- The distinction between component scanning and auto-configuration.
- The class-level `@RequestMapping` shared path versus method-level mappings.
- Why startup-time mapping validation prevents ambiguous runtime behavior.

### Current Implementation State

- The application runs on port `1999`.
- `HomeController` provides `GET /` and `GET /status` plain-text endpoints.
- No URL-shortening functionality, service layer, or persistence exists yet.

### Exact Stopping Point and Next Learning Objective

The two distinct GET endpoints have been restored and work. Next session,
learn `@RequestMapping` as a class-level shared path and reason about how it
combines with `@GetMapping` before designing the real URL-shortener endpoint.
