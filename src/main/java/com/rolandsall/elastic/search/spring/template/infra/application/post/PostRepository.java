package com.rolandsall.elastic.search.spring.template.infra.application.post;

import com.rolandsall.elastic.search.spring.template.core.application.post.command.IPostCreator;
import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostIdGenerator;
import com.rolandsall.elastic.search.spring.template.core.application.post.query.IPostProvider;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostRepository implements IPostProvider, IPostCreator {

    private PostIdGenerator postIdGenerator;

    @Override
    public void addPost(Post post) {
        log.info("received command to add: {}", post);
    }

    @Override
    public List<Post> getAllPosts() {
        log.info("received query to retrieve all posts");
        return null;
    }
}
