package com.rolandsall.elastic.search.spring.template.core.application.post.command;

import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;

public interface IPostCreator {

    void addPost(Post post);

    void editPost(String postId, Comment comment) throws PostNotFoundException;
}
