# Badminton Ranking - Project Context

## Tech Stack
- Java 17
- Spring Boot 3.x
- PostgreSQL
- Flyway
- Spring Data JPA
- Spring Security
- JUnit 5
- Mockito

## Project Goal
Website xếp hạng cầu lông.

Normal users:
- Xem bảng xếp hạng
- Xem thông tin vận động viên

Admin:
- Thêm vận động viên
- Sửa vận động viên
- Xóa vận động viên
- Cập nhật kết quả

## Current Progress

### Authentication
- User entity: DONE
- UserRepository: DONE
- RegisterRequest: DONE
- RegisterResponse: DONE
- UserService: DONE
- UserServiceImpl: DONE
- UserController: DONE
- GlobalExceptionHandler: DONE
- SecurityConfig: DONE

### Testing
- UserServiceImplTest: IN PROGRESS / DONE

## Important Decisions

- Flyway is used for database migrations.
- Never modify an already-applied Flyway migration.
- Create a new migration such as V2, V3...
- DTOs are used instead of exposing User entity directly.
- Password is never returned in response.
- User role defaults to USER.
- Password is encoded using BCrypt.