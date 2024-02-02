package ms.management.client;

import ms.management.dtos.v1.request.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "ms-authentication")
public interface AuthenticationClient {
    @GetMapping(value = "/users/me/{id}")
    public ResponseEntity<UserDTO> getUser(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,  @PathVariable Long id);
}
