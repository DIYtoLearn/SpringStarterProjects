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
| IoC | 3 | Can explain that Spring, rather than application code, creates and connects managed objects. |
| Dependency Injection | 4 | Implemented constructor injection of one shared service into two controllers with guidance. |
| Beans | 3 | Understands that an `@Service` discovered by component scanning is a Spring-managed bean shared by both controllers in this application context. |
| REST Controllers | 4 | Implemented a `@RestController` with two `@GetMapping` methods using a syntax skeleton; understands that the annotation marks the class, not a URL path. |
| Request mapping | 4 | Used class-level `@RequestMapping("/api")` with method-level mappings; can explain that Spring routes by HTTP method plus the combined path. |
| `@PostMapping` | 3 | Implemented and tested a POST-only endpoint; correctly interpreted `405 Method Not Allowed` after sending GET to it. |
| `@RequestBody` | 4 | Bound JSON into `CreateUrlRequest` and extracted `url` through its getter. Understands that Spring converts the body into the declared Java type. |
| JSON content type / `consumes` | 3 | Tested `consumes = application/json` and connected an incorrect content type with `415 Unsupported Media Type`. |
| `@PathVariable` | 4 | Implemented a path-variable lookup for a generated short code. |
| `ResponseEntity` / HTTP responses | 4 | Implemented `404 Not Found` for an unknown code and a `302 Found` response with a `Location` header for a redirect. |
| In-memory maps | 4 | Implemented duplicate lookup, collision-safe code generation, insertion, and reverse lookup with two `HashMap`s; understands that maps moved to the shared service remain temporary. |
| Validation | 0 | |
| Spring Data JPA | 4 | Replaced the service's active map operations with repository duplicate lookup, short-code collision checks, entity saving, and redirect lookup; manually verified persistence across two application restarts. |
| JPA entity mapping | 4 | Can map fields to `url_mapping` columns using `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, and `@Column`; constructed and saved `UrlMapping` entities through the service. |
| MySQL datasource configuration | 3 | Configured a JDBC URL and username in `application.properties`; keeps the password in an IntelliJ environment variable resolved through a property placeholder. |
| Hibernate schema generation | 3 | Observed `spring.jpa.hibernate.ddl-auto=update` create the table and add columns as the entity was developed. |
| Spring Data derived queries | 4 | Declared and used `findByOriginalUrl`, `findByLinkKey`, and `existsByLinkKey`; understands that method names use Java property names, not SQL column names. |
| `Optional` mapping and method references | 2 | Explored how `Optional<UrlMapping>.map(UrlMapping::getOriginalUrl).orElse(null)` transforms a present entity into a `String` while preserving the current `404` behavior for a missing code. |
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
- JSON request binding: distinguish receiving the entire raw JSON document as a
  `String` from binding its `url` property into a Java object.
- In-memory storage: explain why `urlToShortCode` and `shortCodeToUrl` serve
  different lookup directions, and why controller-held maps disappear on restart.
- Bean scope and lifetime: reinforce why both controllers receive the same
  service bean in the current application context, while all map data is still
  lost when that context is restarted.
- Persistence motivation: explain why a database, rather than a different
  controller or service object, is needed to retain mappings across restarts.
- JPA roles: distinguish the JPA mapping API, Hibernate as the provider that
  performs the mapping work, and Spring Data JPA as the repository abstraction.
- Entity and schema mapping: reinforce that the Java property `linkKey` can map
  to the database column `link_key`, and that `ddl-auto=update` is a convenient
  development tool rather than a production schema-migration strategy.
- Repository methods: practise how `Optional<UrlMapping>` represents an absent
  query result and how `findBy...` / `existsBy...` names are derived from entity
  properties.
- Database-generated timestamps: reinforce why `insertable = false` lets
  MySQL's `DEFAULT CURRENT_TIMESTAMP` supply `created_at`.
- Repository persistence testing: distinguish the manually verified API flow
  from automated tests that prove the duplicate and new-mapping service paths
  repeatedly and safely.

## Current Objective

Continue Phase 5 by designing and adding the first automated tests for the
database-backed service. Begin with the existing-URL path and the new-URL path;
then decide what should be mocked and what should use a real test database.
The production API has already been manually verified across restarts.

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

## Session Notes - 2026-08-15 (Request Mapping, POST, JSON, and In-Memory Maps)

- Added class-level `@RequestMapping("/api")`. This made the existing handlers
  available at `GET /api` and `GET /api/status`; the former `/status` path now
  correctly returns `404`.
- Investigated the difference between `/api` and `/api/`. A method mapped with
  `@GetMapping("/")` matched the trailing-slash path, while `@GetMapping()`
  matched `/api` after the mapping was adjusted.
- Added `POST /api/urls`, tested it with Postman, and correctly noted that a
  browser address bar normally sends GET rather than POST.
- Sent GET to the POST-only URL and interpreted the `405 Method Not Allowed`
  response as Spring finding the path but rejecting the unsupported method.
- Restricted the endpoint with `consumes = MediaType.APPLICATION_JSON_VALUE`.
  Tested the matching `Content-Type` header and learned that an unsupported
  media type results in `415 Unsupported Media Type`.
- Received the JSON body with `@RequestBody String requestBody`; observed that
  it is the complete JSON document, for example `{"url":"https://example.com"}`.
- Compared an `ArrayList` of pairs with a `HashMap` design, then selected two
  maps: `urlToShortCode` for duplicate detection and `shortCodeToUrl` for the
  later redirect lookup.
- Added both maps as controller fields and printed their current state. They
  are intentionally still empty because creation and insertion logic have not
  been implemented yet.

## Session Notes - 2026-08-17 (DTO Binding, In-Memory Creation, and Redirects)

- Replaced raw `@RequestBody String` handling with `@RequestBody
  CreateUrlRequest`. Spring binds the JSON `url` property to the DTO through
  its JavaBean setter, and the controller reads it through `getUrl()`.
- Implemented the URL-creation flow: duplicate long URLs return their existing
  short link; new URLs receive a generated code and are stored in both maps.
- Identified that retrying a short-code collision only once could still allow a
  second collision to overwrite a mapping. Replaced that logic with a
  `do...while` loop that continues until a unique code is produced.
- Corrected the exclusive upper bounds used by `Random.nextInt(origin, bound)`,
  allowing `Z`, `z`, and `9` to be generated.
- Added `GET /api/{shortCode}` using `@PathVariable` and verified that it
  resolves a stored code.
- Replaced the temporary `200 OK` plain-text lookup response with
  `ResponseEntity<Void>`: an unknown code produces `404 Not Found`; a known
  code produces `302 Found` and a `Location` header using `URI.create(...)`.
- Tested the redirect successfully. The client, rather than this application,
  follows the `Location` header to the destination URL.
- Observed that the class-level `@RequestMapping("/api")` means the currently
  working redirect is `/api/{shortCode}`. The intended final public route is
  `/{shortCode}`, which will require separating shared URL logic from the API
  controller.

## Session Notes - 2026-08-17 (Service Layer, IoC, and Dependency Injection)

- Explained that direct `new UrlShortenerService()` in each controller would
  create separate service objects with separate map instances. The learner
  correctly identified this Java object-state issue before implementation.
- Created `UrlShortenerService` with `@Service`; it owns the two maps,
  duplicate lookup, collision-safe code generation, insertion, and original
  URL lookup.
- Corrected two map-direction mistakes during review: a duplicate long URL
  must read `urlToShortCode`, while a short-code redirect lookup must read
  `shortCodeToUrl`.
- Injected the service through constructors in `HomeController` and
  `RedirectController`. No controller creates the service with `new`.
- Removed map state and URL-shortening business logic from `HomeController`.
  It now binds the request, calls the service, and returns the short URL.
- Created `RedirectController` without the `/api` class-level mapping and
  moved redirect handling there. The public route is now `GET /{shortCode}`;
  `POST /api/urls` returns a root-level short URL.
- Manually verified the end-to-end flow and ran `./mvnw test` successfully.
- Reinforce conventional Java naming during a later cleanup: package names
  should be lowercase (`service`) and local variables lower camel case
  (`originalUrl`).

## Session Notes - 2026-08-20 (State Reconstruction and Persistence Setup)

- Reconstructed the project state from the learning records, current source,
  Maven configuration, and recent Git history. No application code or
  configuration was changed during this session.
- Confirmed that Phase 4 remains complete: the two controllers share one
  Spring-managed `UrlShortenerService`, while its two `HashMap` instances are
  still the only storage mechanism.
- Confirmed that the project has not yet introduced a JPA dependency, a MySQL
  driver, an entity, or a repository. The next work therefore remains the
  first conceptual step of Phase 5 rather than an implementation task.
- Began the persistence discussion by asking why state held by a service bean
  disappears when the application process restarts, while database state can
  outlive that process. No answer or implementation was completed before the
  session ended.

## Session Notes - 2026-08-20 (Phase 5: MySQL, Entity Mapping, and Repository Setup)

- Correctly explained that the service maps live in the JVM heap and disappear
  when the Spring Boot process stops, whereas MySQL data is stored outside that
  process and survives an application restart.
- Reviewed the `URL_MAPPING` model: a generated internal `id`, unique
  nine-character `link_key`, direct `original_url` storage, and a creation
  timestamp. Clarified that an application-level duplicate check works for the
  normal sequential path, while a database constraint would later protect the
  same rule under concurrent requests.
- Created the local `urlshortener` MySQL database. Added Spring Data JPA and the
  MySQL JDBC driver to Maven, configured the datasource on port 3306, and kept
  the password outside source control through the `URLSHORTENER_DB_PASSWORD`
  environment-variable placeholder in the IntelliJ run configuration.
- Learned that `String` is not an appropriate Java type for a timestamp; chose
  `LocalDateTime` for the entity property and configured MySQL to provide the
  initial value with `DEFAULT CURRENT_TIMESTAMP`.
- Created `UrlMapping` as a JPA entity mapped to `url_mapping`. Incrementally
  observed Hibernate create the table and add `id`, `link_key`, and
  `original_url` after application restarts. The timestamp mapping was then
  added with the database as its writer.
- Encountered the modern `jakarta.persistence` namespace and an IntelliJ Maven
  synchronization issue. The dependency was already correct; reloading the
  Maven project made the annotations resolvable. Reinforce that Java package
  names are case-sensitive and Spring Boot uses `jakarta.persistence`, not the
  older `javax.persistence` package.
- Created `UrlMappingRepository extends JpaRepository<UrlMapping, Long>` and
  added the derived methods `findByOriginalUrl` and `findByLinkKey`, both
  returning `Optional<UrlMapping>`. The planned `existsByLinkKey` method was
  discussed but had not yet been added when the session ended.

## Session Notes - 2026-08-21 (Phase 5: Repository-Backed Service)

- Reviewed learner-led changes to the persistence implementation. Added
  `existsByLinkKey` to the repository and constructor-injected the repository
  into `UrlShortenerService`.
- Replaced the active duplicate lookup with `findByOriginalUrl`. When an
  `Optional<UrlMapping>` is present, the service returns its stored `linkKey`.
- Fixed an important collision-loop error during review. A code existence result
  must be checked for the same candidate produced in that loop iteration;
  retaining one `Optional` outside the loop would either fail to check a later
  candidate or loop forever. The final loop generates a candidate and uses
  `existsByLinkKey(shortCode)` in its condition.
- Created `new UrlMapping(shortCode, originalUrl)` and passed it to repository
  `save(...)`, replacing the active map insertion. The database supplies the
  generated ID and creation timestamp.
- Replaced redirect lookup with `findByLinkKey(shortCode)` followed by
  `.map(UrlMapping::getOriginalUrl).orElse(null)`. This chain changes
  `Optional<UrlMapping>` into `Optional<String>`, then preserves the current
  controller contract by returning `null` when the code is missing; the
  controller consequently continues to return `404`.
- Manually tested all current endpoints, repeated the application restart twice,
  and confirmed that a previously created mapping persists in MySQL. Also
  verified that duplicate URL submission returns the existing code, only one row
  remains, and an unknown short code returns `404`.
- The learner chose to retain the old in-memory map code as comments for
  learning reference. These comments are inactive and do not participate in the
  running implementation; the repository is now the active data source.
- No automated tests were added during this session. The immediate next task is
  to design service tests for the existing-URL and new-URL paths.
