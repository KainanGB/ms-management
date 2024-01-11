package ms.management.dtos;

public record Message<T>(
    String correlationId,
   T payload
) {


}
