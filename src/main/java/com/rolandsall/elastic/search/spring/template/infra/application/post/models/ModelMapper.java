package com.rolandsall.elastic.search.spring.template.infra.application.post.models;

import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic.PostIndexModel;

import java.util.List;

public interface ModelMapper {
    PostIndexModel ToPersistenceEntity(Post post);

    List<Post> ToDomainModel(List<PostIndexModel> posts);
}
