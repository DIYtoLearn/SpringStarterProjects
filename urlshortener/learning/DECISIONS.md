# Architecture Decisions

## ADR-001 — Project Choice

### Decision

Build a URL Shortener as the first Spring Boot project.

### Reason

It provides practical exposure to REST APIs, validation, business
logic, persistence, HTTP redirects, and testing without excessive
domain complexity.

---

## ADR-002 — Initial Layering

### Decision

The initial application will use:

Controller
→ Service
→ Repository
→ Persistence

### Status

Accepted.

---

## ADR-003 — URL Creation Endpoint

### Decision

Create shortened URLs through:

POST /api/urls

### Status

Accepted.

---

## ADR-004 — Redirect Endpoint

### Decision

Resolve a short code through:

GET /{shortCode}

and redirect the client to the original URL.

### Status

Accepted.

---

## ADR-005 — Phase 1 Dependencies

### Decision

Keep Spring Web MVC as the only production Spring dependency during Phase 1.
Keep web test support only for the generated context-load test.

### Reason

Database persistence, validation, and Lombok are planned for later phases and
would distract from learning the initial Spring Boot structure and startup.

### Status

Accepted.

---

## ADR-006 - API Base Path

### Decision

Use class-level `@RequestMapping("/api")` for controller API endpoints.
The current endpoints are `GET /api`, `GET /api/status`, and `POST /api/urls`.

### Reason

The shared prefix groups API routes and leaves a future root-level short-code
redirect route available without mixing it with API endpoints.

### Status

Accepted.

---

## ADR-007 - Phase 3 In-Memory Duplicate Lookup

### Decision

Use two maps: `urlToShortCode` for long-URL duplicate lookup and
`shortCodeToUrl` for short-code resolution.

### Reason

The URL-creation flow needs to find an existing code by long URL, while the
future redirect flow needs to find the long URL by short code. Each map makes
its needed lookup direct and clear for this learning phase.

### Status

Accepted for the in-memory learning implementation; persistence will replace
these maps in a later phase.

---

## ADR-008 - Temporary Redirect Route During Controller-Only Phase

### Decision

The current redirect endpoint is `GET /api/{shortCode}` because it is defined
inside the API controller, which has class-level `@RequestMapping("/api")`.

### Reason

Keeping all in-memory state in one controller allowed the learner to complete
the initial DTO, map, and redirect exercises before introducing Spring beans
and dependency injection.

### Status

Superseded by ADR-009.

---

## ADR-009 - Shared In-Memory URL Shortener Service and Public Redirect Route

### Decision

Use one Spring-managed `UrlShortenerService` to own the temporary in-memory
maps and URL-shortening operations. Inject it through constructors into the
API and redirect controllers. Expose redirects at `GET /{shortCode}` through
a separate controller without the `/api` base path.

### Reason

Creation and redirection require the same mappings but have different HTTP
responsibilities. A shared service avoids duplicate state and business logic,
keeps controllers focused on request/response handling, and preserves the API
prefix while making short links publicly accessible at the root.

### Status

Accepted for the in-memory learning implementation. A repository and database
will replace the map storage in the persistence phase.

---

## ADR-010 - Initial MySQL Persistence Mapping

### Decision

Use the local MySQL database `urlshortener` during the Phase 5 learning work.
Map `UrlMapping` to the `url_mapping` table with these columns:

- generated `id` primary key;
- unique `link_key`, with a maximum length of nine characters;
- non-null `original_url`, with an initial maximum length of 2048 characters;
- `created_at`, supplied by MySQL through `DEFAULT CURRENT_TIMESTAMP`.

Configure Spring Boot through `spring.datasource.*`; keep the password outside
the tracked properties file in the `URLSHORTENER_DB_PASSWORD` environment
variable. Use `spring.jpa.hibernate.ddl-auto=update` only for this local learning
and development phase.

### Reason

The database must outlive the application JVM so mappings survive restarts.
The entity mapping introduces JPA/Hibernate incrementally and lets the learner
observe the relationship between Java fields and MySQL columns. The database
enforces public short-code uniqueness; duplicate original-URL protection remains
an application-level concern for now and will be revisited when concurrency is
introduced.

### Status

Accepted for Phase 5 development. Production schema migrations and a
database-level original-URL uniqueness decision are deferred.

---

## ADR-011 - Repository-Backed URL Shortening Service

### Decision

`UrlShortenerService` now uses the injected `UrlMappingRepository` as its active
storage mechanism:

- `findByOriginalUrl` returns an existing short code for a duplicate submission;
- `existsByLinkKey` checks each newly generated short-code candidate;
- `save(new UrlMapping(...))` persists a new mapping;
- `findByLinkKey` resolves the original URL for the redirect path.

The old two-map implementation remains commented in the service at the
learner's request as a Phase 3 learning reference, but it is not active.

### Reason

This preserves the current controller contracts while replacing temporary JVM
heap state with MySQL rows. It also retains idempotent duplicate behavior and
the collision-retry strategy in their repository-backed equivalents.

### Status

Accepted and manually verified across application restarts. Automated tests are
still required before Phase 5 is considered complete.

---

## Open Decisions

Decisions that have not yet been finalized should be recorded here
before implementation rather than invented by the AI.

- Phase 5 testing scope: begin with Mockito unit tests that replace
  `UrlMappingRepository` with a mock and verify service behavior. Whether and
  how to add a JPA/MySQL integration test with a dedicated test database remains
  undecided.
