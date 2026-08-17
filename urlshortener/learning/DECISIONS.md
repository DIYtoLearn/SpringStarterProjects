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

## Open Decisions

Decisions that have not yet been finalized should be recorded here
before implementation rather than invented by the AI.
