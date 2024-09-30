package com.example.demo.remote.clients;

import com.example.demo.model.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yandexDisk", url = "disk.yandex.ru")
public interface RemoteClient {

    @GetMapping
    UserInfoResponse getYaUser(@RequestParam Long id, @RequestHeader("key") String key);

}
