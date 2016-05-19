
# nessie

Spring Boot project running the Monty Hall problem.

## Running

Build and start application using Maven and command `mvn clean install spring-boot:run`. The application runs on port `8080`.

## HTTP Endpoint

Single endpoint at `/nessie/scout` with optional boolean parameter `switch_lake`, i.e. `/nessie/scout?switch_lake=true` (default `false`).

Example: [http://localhost:8080/nessie/scout?switch_lake=true](http://localhost:8080/nessie/scout?switch_lake=true)
