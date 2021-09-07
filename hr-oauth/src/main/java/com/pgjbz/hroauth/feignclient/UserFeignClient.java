package com.pgjbz.hroauth.feignclient;

import com.pgjbz.hroauth.dto.UserResponseDTO;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RefreshScope
@FeignClient(name = "${services.hr-user.name}",  path = "${services.hr-user.path}")
public interface UserFeignClient {

    @GetMapping(value = "/search")
    ResponseEntity<UserResponseDTO> findByEmail (@RequestParam(value = "email") String email);

}
