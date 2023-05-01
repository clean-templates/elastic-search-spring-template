package com.rolandsall.elastic.search.spring.template.infra.application.post.models;


import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Owner;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic.CommentIndexModel;
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
                .replies(CommentToPersistenceEntity(post.getComments()))
                .build();
    }

    @Override
    public List<Post> ToDomainModel(List<PostIndexModel> posts) {
        return posts.stream()
                .map(this::ToDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Post ToDomainModel(PostIndexModel post) {
        return Post.builder()
                .id(post.getId())
                .owner(new Owner(post.getOwner()))
                .content(post.getContent())
                .topic(post.getTopic())
                .createdAt(post.getCreatedAt())
                .comments(CommentToDomain(post))
                .build();
    }

    private static List<CommentIndexModel> CommentToPersistenceEntity(List<Comment> comment) {
        return comment.stream()
                .map(reply -> CommentIndexModel.builder()
                        .id(reply.getId())
                        .owner(reply.getOwner().getId())
                        .content(reply.getContent())
                        .createdAt(reply.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    private static List<Comment> CommentToDomain(PostIndexModel post) {
        return post.getReplies().stream()
                .map(reply -> Comment.builder()
                        .id(reply.getId())
                        .owner(new Owner(reply.getOwner()))
                        .content(reply.getContent())
                        .createdAt(reply.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
