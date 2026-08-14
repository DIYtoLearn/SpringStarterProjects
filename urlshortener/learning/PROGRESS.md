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

The application has not yet been started manually. The next task is to run
`./mvnw spring-boot:run`, inspect the startup log and `localhost:8080`, then
explain what `SpringApplication.run(...)` sets up.
