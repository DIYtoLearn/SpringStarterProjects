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

## 2026-08-17

### Phase 3 Completion — DTO Binding, Short-Code Creation, and Redirect

- Created `CreateUrlRequest` and bound the JSON request body to it with
  `@RequestBody`.
- Extracted the original URL from the DTO, returned the existing short link for
  duplicate URLs, and created a new code for previously unseen URLs.
- Stored each successful mapping in both `urlToShortCode` and
  `shortCodeToUrl`.
- Used a `do...while` loop to regenerate a short code until it is not already a
  key in `shortCodeToUrl`.
- Corrected the random character bounds so the full intended alphanumeric
  ranges are eligible for generation.
- Implemented `GET /api/{shortCode}` with `@PathVariable`.
- Returned `404 Not Found` for unknown short codes and a `302 Found` redirect
  with a `Location` header for known codes. The redirect was tested manually
  and works.
- Ran `./mvnw test`; the existing Spring context test passed (1 test, 0
  failures).

### Concepts Learned

- JSON-to-object binding with `@RequestBody` and a request DTO.
- Getter/setter-based property binding for JSON fields.
- Duplicate detection and bidirectional `HashMap` lookup.
- Why a collision check must repeat until a unique value is produced.
- `@PathVariable` for extracting a value from a URL segment.
- `ResponseEntity` for setting status, headers, and an optional body.
- HTTP `302 Found`, the `Location` header, and `404 Not Found`.

### Concepts to Reinforce

- The difference between returning a URL as response text and returning an
  HTTP redirect response.
- Class-level `@RequestMapping` applies to every method in that controller.
- In-memory `HashMap` state disappears on restart and is not suitable for
  concurrent production traffic.

### Current Implementation State

- `POST /api/urls` accepts JSON, returns an existing or newly generated short
  link, and stores mappings in memory.
- `GET /api/{shortCode}` redirects a known code and returns 404 for an unknown
  code.
- The creation response currently returns plain text and hard-codes
  `http://localhost:1999/api/`.
- The redirect is temporarily under `/api`; the roadmap's intended public
  route is `/{shortCode}`.

### Exact Stopping Point and Next Learning Objective

The in-memory URL shortener works end-to-end and was manually tested. Next,
learn IoC, Spring beans, and constructor dependency injection by extracting
the shared maps and URL-shortening operations into a `UrlShortenerService`.
Then create a separate root-level redirect controller so the final route can
be `GET /{shortCode}` while API routes remain under `/api`.

### Phase 4 Completion — Service Layer, IoC, and Constructor Injection

- Introduced IoC as Spring taking responsibility for creating and connecting
  application objects. Defined a bean as an object managed by Spring's
  application context.
- The learner correctly explained why `new UrlShortenerService()` in each
  controller would be incorrect: each Java object has separate instance fields,
  so the two map pairs would not be shared.
- Created the Spring-managed `@Service` class `UrlShortenerService`.
- Moved `urlToShortCode`, `shortCodeToUrl`, duplicate handling, unique code
  generation, mapping insertion, and short-code lookup from the controller to
  the service.
- Found and corrected a lookup-direction bug while reviewing the service:
  `urlToShortCode.get(originalUrl)` returns the existing code for a duplicate;
  `shortCodeToUrl.get(shortCode)` returns the original URL for redirection.
- Added constructor injection to `HomeController` and `RedirectController`.
  Spring supplies the shared service bean; no `new UrlShortenerService()` is
  used in either controller.
- Simplified `HomeController` so it handles the HTTP request/response boundary
  and delegates URL-shortening work to the service.
- Created `RedirectController` with `GET /{shortCode}`. It delegates lookup to
  the service and returns `302 Found` with `Location` or `404 Not Found`.
- Removed the temporary `/api/{shortCode}` redirect endpoint. `POST /api/urls`
  now returns `http://localhost:1999/{shortCode}`.
- Manually verified a create-then-redirect flow and ran `./mvnw test` after
  the refactor; 1 context-load test passed with 0 failures.

### Concepts Learned

- IoC: Spring controls creation and assembly of managed application objects.
- Beans: `@Service` is a component stereotype that makes the service eligible
  for component scanning and Spring management.
- Constructor injection: a constructor states the object's required
  collaborator; Spring supplies the matching bean when creating the controller.
- Service-layer responsibility: controllers deal with HTTP details while the
  service holds reusable URL-shortening behavior and shared in-memory state.
- Architectural routing: an API controller mapped to `/api` cannot expose a
  root-level endpoint; a separate controller without that base mapping can.

### Concepts to Reinforce

- Spring's service bean is shared in this application context, but its ordinary
  in-memory `HashMap` data still vanishes whenever the application restarts.
- A database provides durable storage beyond an application's process lifetime;
  creating another controller or Java object does not.
- The existing test proves only that the Spring context starts. Endpoint and
  service behavior do not yet have automated tests.

### Current Implementation State

- Port remains `1999`.
- `HomeController`: `GET /api`, `GET /api/status`, and JSON-consuming
  `POST /api/urls`.
- `RedirectController`: `GET /{shortCode}`, returning `302 Found` plus
  `Location` for known codes and `404 Not Found` otherwise.
- `UrlShortenerService`: one shared Spring-managed owner of two in-memory maps
  and create/lookup operations.
- The maps reset on restart and are not intended for concurrent production use.

### Exact Stopping Point and Next Learning Objective

Phase 4 is complete. Next session starts Phase 5 by explaining why application
restart loses the mappings, reviewing the planned `URL_MAPPING` model, and
then introducing Spring Data JPA, Hibernate, and MySQL one concept at a time.
