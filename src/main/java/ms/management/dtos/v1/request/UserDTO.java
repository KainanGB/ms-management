package ms.management.dtos.v1.request;

public record UserDTO(
    Long id,
    String email,
    String created_at
) {
}
