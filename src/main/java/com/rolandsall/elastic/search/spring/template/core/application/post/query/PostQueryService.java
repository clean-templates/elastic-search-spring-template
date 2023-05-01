package com.rolandsall.elastic.search.spring.template.core.application.post.query;

import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostQueryService implements IPostQueryService {

    private final IPostProvider postProvider;

    @Override
    public List<Post> getAllPosts() {
        return postProvider.getAllPosts();
    }

    @Override
    public List<Post> getPostByTopic(List<String> topics) {
        return postProvider.getPostByTopic(topics);
    }

    @Override
    public Post findById(String postId) throws PostNotFoundException {
        return postProvider.findById(postId);
    }
}
