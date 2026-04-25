![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen?logo=springboot)
![Resilience4j](https://img.shields.io/badge/Resilience4j-Retry%20%7C%20CircuitBreaker%20%7C%20Timeout%20%7C%20Fallback-orange)

# Resilient Payment API

Simple Spring Boot API to simulate payment processing with external partners, focusing on resilience patterns.

This project demonstrates how to handle failures when calling unreliable external services.

## Overview

- Simulates money transfers to an external partner
- Introduces failures (errors and slow responses)
- Applies resilience patterns to handle instability:
  - Retry
  - Circuit Breaker
  - Timeout
  - Fallback

## Resilience Flow

`Request → Retry → CircuitBreaker → Timeout → Fallback`

- **Retry** → retries transient failures
- **Circuit Breaker** → stops calling the partner if failure rate is high
- **Timeout** → avoids waiting indefinitely for slow responses
- **Fallback** → returns a controlled response when everything fails

## Running the Application

Run the application:

```
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Testing the API

Trigger a transfer:

```
POST http://localhost:8080/transfers
```

The fake partner will randomly:
- succeed
- respond slowly
- fail with errors

Observe how the system behaves under these conditions.

A [Postman collection](https://github.com/franciscoreinalondon/resilient-payment-api/tree/main/postman)
is included in this repository.

## Example Behaviours

- **Slow response** → timeout → fallback
- **Repeated failures** → circuit breaker opens
- **Circuit open** → fail fast (no external call)
- **Recovery** → circuit transitions to HALF_OPEN → CLOSED

## Notes

- Designed as an example project for resilience patterns in distributed systems
- External partner is simulated via /partner/send
- Focus is on system behaviour under failure, not persistence or business logic

## Future Improvements

- **Bulkhead** → isolate resources (prevent a failure from affecting the entire system)
- **Rate limiting** → protect against traffic spikes
- **Queues (Kafka, etc.)** → decouple systems
- **Idempotency** → prevent duplicate operations (critical in payments)
- **Asynchronous retries** (non-blocking)
- **Dead Letter Queues** → handle persistent failures
- **Observability** (tracing, metrics)
