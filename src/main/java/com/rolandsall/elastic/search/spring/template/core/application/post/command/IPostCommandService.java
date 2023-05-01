package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import com.rolandsall.elastic.search.spring.template.core.application.post.command.dto.PostRequest;
import com.rolandsall.elastic.search.spring.template.core.domain.Comment;

public interface IPostCommandService {

    void addPost(PostRequest post);


    void editPost(String postId, Comment comment) throws PostNotFoundException;
}
