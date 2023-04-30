package com.rolandsall.elastic.search.spring.template.infra.application.post.models;


import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic.PostIndexModel;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ElasticModelMapper implements ModelMapper {

    @Override
    public PostIndexModel ToPersistenceEntity(Post post) {
        return PostIndexModel.builder()
                .id(post.getId())
                .topic(post.getTopic())
                .createdAt(post.getCreatedAt())
                .content(post.getContent())
                .replies(Collections.emptyList())
                .build();
    }
}
