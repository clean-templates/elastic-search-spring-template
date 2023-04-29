package com.rolandsall.elastic.search.spring.template.core.application.post.query;

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
}
