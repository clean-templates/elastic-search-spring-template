package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommandService implements IPostCommandService{

    private final IPostCreator postCreator;


    @Override
    public void addPost(Post post) {
        postCreator.addPost(post);
    }
}
