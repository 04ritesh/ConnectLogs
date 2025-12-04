# Likes Service API Documentation

## Base URL
```
http://localhost:8084/api/likes
```

## Endpoints

### 1. Like an Experience
**POST** `/experience/{experienceId}/user/{userId}`

Like an experience for a specific user.

**Parameters:**
- `experienceId` (Long): ID of the experience to like
- `userId` (Long): ID of the user liking the experience

**Response:**
- Status: 200 OK
- Body: "Experience liked successfully"

**Example:**
```bash
curl -X POST http://localhost:8084/api/likes/experience/1/user/123
```

### 2. Unlike an Experience
**DELETE** `/experience/{experienceId}/user/{userId}`

Remove a like from an experience for a specific user.

**Parameters:**
- `experienceId` (Long): ID of the experience to unlike
- `userId` (Long): ID of the user unliking the experience

**Response:**
- Status: 200 OK
- Body: "Experience unliked successfully"

**Example:**
```bash
curl -X DELETE http://localhost:8084/api/likes/experience/1/user/123
```

### 3. Check Like Status
**GET** `/experience/{experienceId}/user/{userId}/status`

Check if a user has liked a specific experience.

**Parameters:**
- `experienceId` (Long): ID of the experience
- `userId` (Long): ID of the user

**Response:**
- Status: 200 OK
- Body: `true` if liked, `false` if not liked

**Example:**
```bash
curl http://localhost:8084/api/likes/experience/1/user/123/status
```

### 4. Get Likes Count
**GET** `/experience/{experienceId}/count`

Get the total number of likes for an experience.

**Parameters:**
- `experienceId` (Long): ID of the experience

**Response:**
- Status: 200 OK
- Body: Integer count of likes

**Example:**
```bash
curl http://localhost:8084/api/likes/experience/1/count
```

### 5. Get Experience Details
**GET** `/experience/{id}`

Get experience details (proxied from Experience service).

**Parameters:**
- `id` (Long): ID of the experience

**Response:**
- Status: 200 OK
- Body: ExperienceResponse object

**Example:**
```bash
curl http://localhost:8084/api/likes/experience/1
```

## Database Schema

### EXPERIENCE_LIKES Table
```sql
CREATE TABLE experience_likes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    experience_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    UNIQUE(experience_id, user_id)
);
```

## Service Configuration

### Application Properties
- **Port:** 8084
- **Database:** H2 in-memory (for development)
- **H2 Console:** http://localhost:8084/h2-console

### Dependencies
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Web
- H2 Database
- Lombok
- Validation

## Testing the Service

1. **Start the service:**
```bash
cd d:\Desktop\ConnectLogs\Likes-service
mvnw spring-boot:run
```

2. **Test endpoints using curl or Postman:**
```bash
# Like an experience
curl -X POST http://localhost:8084/api/likes/experience/1/user/123

# Check if liked
curl http://localhost:8084/api/likes/experience/1/user/123/status

# Get likes count
curl http://localhost:8084/api/likes/experience/1/count

# Unlike an experience
curl -X DELETE http://localhost:8084/api/likes/experience/1/user/123
```

3. **Access H2 Console for database inspection:**
- URL: http://localhost:8084/h2-console
- JDBC URL: jdbc:h2:mem:likesdb
- Username: sa
- Password: password