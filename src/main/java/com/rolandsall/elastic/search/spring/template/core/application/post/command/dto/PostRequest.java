package com.rolandsall.elastic.search.spring.template.core.application.post.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String ownerId;
    private String content;
    private List<String> topics;
}
