## Setup & Run
build project & artifact to ./loatrack/backend/target/loatrack.jar

## Docker Setup

```bash
mvn clean install
docker build -t loatrack-backend .
docker run -p 8100:8100 loatrack-backend
```