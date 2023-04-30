package com.rolandsall.elastic.search.spring.template.infra.application.post.models;


import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Owner;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic.PostIndexModel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticModelMapper implements ModelMapper {

    @Override
    public PostIndexModel ToPersistenceEntity(Post post) {
        return PostIndexModel.builder()
                .id(post.getId())
                .topic(post.getTopic())
                .createdAt(post.getCreatedAt())
                .owner(post.getOwner().getId())
                .content(post.getContent())
                .replies(Collections.emptyList())
                .build();
    }

    @Override
    public List<Post> ToDomainModel(List<PostIndexModel> posts) {
        return posts.stream()
                .map(post -> Post.builder()
                        .id(post.getId())
                        .owner(new Owner(post.getOwner()))
                        .content(post.getContent())
                        .topic(post.getTopic())
                        .createdAt(post.getCreatedAt())
                        .comments(post.getReplies().stream()
                                .map(reply -> Comment.builder()
                                        .id(reply.getId())
                                        .owner(new Owner(reply.getOwner()))
                                        .content(reply.getContent())
                                        .createdAt(reply.getCreatedAt())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
