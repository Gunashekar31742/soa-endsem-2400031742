# Tourist Service

Simple token-protected tourist lookup API using PostgreSQL database `exam_db`.

Set `DB_PASSWORD` to the PostgreSQL password and `TOURIST_API_TOKEN` to the
pre-generated valid token before starting the service. No token generation is
included.

Run from this directory with `mvn spring-boot:run`. Swagger UI is available at
`http://localhost:7006/swagger-ui.html`.

Create a tourist with `POST /api/tourist`, using JSON such as
`{"name":"Alex Tourist","email":"alex@example.com","phone":"555-0100","country":"Example"}`.
Then use `GET /api/tourist/{id}` with the ID returned by POST. Pass the token in
the `X-Auth-Token` header for both requests. Valid token and existing record
returns 200 for GET; successful POST returns 201; invalid or missing token
returns 401; a valid token with no matching tourist returns 404.
