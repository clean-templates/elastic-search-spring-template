package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.application.post.command.dto.PostRequest;
import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostIdGenerator;
import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostTimeGenerator;
import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Owner;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommandService implements IPostCommandService {

    private final IPostCreator postCreator;
    private final PostIdGenerator postIdGenerator;
    private final PostTimeGenerator postTimeGenerator;


    @Override
    public void addPost(PostRequest postRequest) {
        Post post = buildPostFrom(postRequest);
        postCreator.addPost(post);
    }

    @Override
    public void updatePost(Post post, Comment comment) {
        post.getComments().add(comment);
        postCreator.addPost(post);
    }

    private Post buildPostFrom(PostRequest postRequest) {
        return Post.builder()
                .id(postIdGenerator.generateId())
                .content(postRequest.getContent())
                .createdAt(postTimeGenerator.generateTime())
                .owner(Owner.builder().id(postRequest.getOwnerId()).build())
                .topic(postRequest.getTopics())
                .build();
    }
}
