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
