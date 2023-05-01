package com.rolandsall.elastic.search.spring.template.core.application.comment.command;

import com.rolandsall.elastic.search.spring.template.core.application.comment.command.dto.CommentRequest;
import com.rolandsall.elastic.search.spring.template.core.application.post.query.IPostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCommandService implements ICommentCommandService {

    private IPostQueryService postQueryService;

    @Override
    public void addComment(String postId, CommentRequest request) {
        postQueryService.findById(postId);
    }
}
