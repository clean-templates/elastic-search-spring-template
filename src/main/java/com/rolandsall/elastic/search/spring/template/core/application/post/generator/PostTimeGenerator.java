package com.rolandsall.elastic.search.spring.template.core.application.post.generator;

import java.time.ZonedDateTime;

public interface PostTimeGenerator {

    ZonedDateTime generateTime();
}
