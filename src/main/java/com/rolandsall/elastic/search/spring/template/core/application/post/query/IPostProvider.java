package com.rolandsall.elastic.search.spring.template.core.application.post.query;

import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;

import java.util.List;

public interface IPostProvider {

    List<Post> getAllPosts();

    List<Post> getPostByTopic(List<String> topics);

    Post findById(String postId) throws PostNotFoundException;
}
