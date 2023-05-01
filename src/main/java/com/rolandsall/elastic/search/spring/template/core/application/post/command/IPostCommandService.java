package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.application.post.command.dto.PostRequest;
import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;

public interface IPostCommandService {

    void addPost(PostRequest post);

    void updatePost(Post post, Comment comment);
}
