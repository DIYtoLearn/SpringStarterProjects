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

## Open Decisions

Decisions that have not yet been finalized should be recorded here
before implementation rather than invented by the AI.
