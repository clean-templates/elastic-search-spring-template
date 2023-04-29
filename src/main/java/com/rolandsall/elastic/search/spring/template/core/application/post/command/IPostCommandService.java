package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.application.post.command.dto.PostRequest;

public interface IPostCommandService {

    void addPost(PostRequest post);
}
