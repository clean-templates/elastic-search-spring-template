package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.domain.Post;

public interface IPostCreator {

    void addPost(Post post);
}
