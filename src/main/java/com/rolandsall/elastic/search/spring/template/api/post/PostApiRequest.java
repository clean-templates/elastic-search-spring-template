package com.rolandsall.elastic.search.spring.template.api.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostApiRequest {
    private String ownerId;
    private String content;
    private List<String> topic;
}
