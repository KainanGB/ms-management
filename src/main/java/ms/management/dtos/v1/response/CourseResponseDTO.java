package ms.management.dtos.v1.response;

import java.sql.Timestamp;

public record CourseResponseDTO(
    Long id,
    Long instructor_id,
    String title,
    String category,
    Timestamp created_at,
    Timestamp updated_at) {
}
