package com.rolandsall.elastic.search.spring.template.infra.application.post.generator;

import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostIdGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostUUIDGenerator implements PostIdGenerator {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
