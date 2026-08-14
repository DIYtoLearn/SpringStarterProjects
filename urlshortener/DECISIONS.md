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

## Open Decisions

Decisions that have not yet been finalized should be recorded here
before implementation rather than invented by the AI.