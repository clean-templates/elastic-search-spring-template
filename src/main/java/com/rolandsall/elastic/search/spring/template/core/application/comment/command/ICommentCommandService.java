package com.rolandsall.elastic.search.spring.template.core.application.comment.command;

import com.rolandsall.elastic.search.spring.template.core.application.comment.command.dto.CommentRequest;
import com.rolandsall.elastic.search.spring.template.core.application.exceptions.ElasticQueryClientException;

public interface ICommentCommandService {


    void addComment(String postId, CommentRequest request) throws ElasticQueryClientException;
}
