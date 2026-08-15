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

### Phase 2 Completion and Phase 3 Start - POST, JSON, and In-Memory Storage

- Added class-level `@RequestMapping("/api")` and verified that the controller
  endpoints moved beneath the `/api` base path.
- Confirmed `GET /api/status` works while the old `GET /status` correctly
  returns `404 Not Found`.
- Compared `/api` with `/api/` and adjusted the root method mapping so the
  intended no-trailing-slash path is handled.
- Added and tested `POST /api/urls` through Postman.
- Observed `405 Method Not Allowed` when sending GET to the POST-only endpoint.
- Added an `application/json` `consumes` restriction and associated a wrong
  content type with `415 Unsupported Media Type`.
- Received the body through `@RequestBody String` and observed that it contains
  the whole JSON document rather than only the URL value.
- Chose two in-memory maps, `urlToShortCode` and `shortCodeToUrl`, for
  duplicate checking and reverse lookup; added them as controller fields and
  printed their current state.

### Concepts Learned

- Class-level and method-level request mappings combine to form an endpoint.
- An endpoint mapping includes both the URL path and the HTTP method.
- `@PostMapping` is a POST-specific request mapping.
- `consumes` matches the request `Content-Type`.
- `@RequestBody String` receives raw request content.
- A `HashMap` provides key-to-value lookup; two maps support efficient lookup
  in both directions.

### Concepts to Reinforce

- Trailing-slash path matching is distinct from HTTP-method matching.
- JSON text is not yet the same thing as the Java URL string needed as a key.
- Controller-held in-memory maps are temporary and reset whenever the
  application restarts.

### Current Implementation State

- The application runs on port `1999`.
- `HomeController` handles `GET /api`, `GET /api/status`, and JSON-consuming
  `POST /api/urls`.
- `POST /api/urls` currently echoes the raw JSON body and prints two empty
  maps; it does not yet create a code or store a mapping.

### Exact Stopping Point and Next Learning Objective

Stopped immediately after identifying that using `requestBody` directly would
store the whole JSON document as a map key. The learner correctly concluded
that the `url` field must be extracted as a Java `String` before looking in
`urlToShortCode`. Next, introduce a small request DTO for `POST /api/urls`,
bind the JSON body with `@RequestBody`, and use the extracted URL string for
duplicate lookup.
