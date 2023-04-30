package com.rolandsall.elastic.search.spring.template.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private String id;
    private Owner owner;
    private String content;
    private List<String> topic;
    private ZonedDateTime createdAt;

    @Builder.Default
    private List<Comment> comments = new ArrayList<>();
}
