package com.rolandsall.elastic.search.spring.template.api.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping()
    public ResponseEntity<Object> getHealth(){
        return ResponseEntity.ok().build();
    }
}
