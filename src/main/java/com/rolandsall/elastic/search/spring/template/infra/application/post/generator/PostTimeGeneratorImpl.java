package com.rolandsall.elastic.search.spring.template.infra.application.post.generator;

import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostTimeGenerator;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class PostTimeGeneratorImpl implements PostTimeGenerator {


    @Override
    public ZonedDateTime generateTime() {
        return ZonedDateTime.now();
    }
}
