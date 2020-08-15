package me.vvsos1.demobootaws.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {

    @GetMapping("/port")
    public String port(@Value("${server.port:8080}") Integer port) {
        // 현재 실행중인 포트; 기본값은 8080
        return port.toString();
    }

}
