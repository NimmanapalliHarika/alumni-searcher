# FreightFox Alumni LinkedIn Profile Searcher

Java 21 / Spring Boot REST API starter. No UI, as requested in the assignment.

## Run locally (fastest)
1. Import this folder into Spring Tool Suite using **File > Import > Maven > Existing Maven Projects**.
2. Wait for Maven dependencies to download.
3. Run `AlumniProfileSearcherApplication.java` as **Spring Boot App**.
4. Open Swagger: http://localhost:8080/swagger-ui.html

The default local profile uses in-memory H2 so PostgreSQL installation is not needed for local development. Data is temporary and clears when the app stops.

## APIs
- `POST /api/alumni/search`
```json
{"university":"University of XYZ","designation":"Software Engineer","passoutYear":2020}
```
- `GET /api/alumni/all`

## PostgreSQL profile
Set environment variables `DATABASE_URL`, `DATABASE_USERNAME`, `DATABASE_PASSWORD`, then run with profile `postgres`:
`java -jar target/alumni-profile-searcher-0.0.1-SNAPSHOT.jar --spring.profiles.active=postgres`

For hosted PostgreSQL, use the provider's JDBC URL, usually beginning `jdbc:postgresql://...`.

## PhantomBuster
Set `PHANTOMBUSTER_API_KEY` and `PHANTOMBUSTER_AGENT_ID` as environment variables. The launch API is asynchronous and each Phantom has its own argument/output schema. Update `PhantomBusterService` to match your configured Phantom's input and output. Until that is implemented/configured, the search endpoint returns a clear service-unavailable error; it does not fabricate LinkedIn profiles.

## Important before submission
The assignment expects real PhantomBuster-backed search and persisted PostgreSQL records. H2 is only a local fallback. Do not describe a local H2 run or placeholder PhantomBuster integration as a fully completed production deployment.
