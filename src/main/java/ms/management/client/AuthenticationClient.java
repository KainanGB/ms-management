package ms.management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("ms-authentication")
public interface AuthenticationClient {
    @RequestMapping(method = RequestMethod.GET, value = "/auth/1")
    public String getName();
}
