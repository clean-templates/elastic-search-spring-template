package com.rolandsall.elastic.search.spring.template.core.application.comment.command;

import com.rolandsall.elastic.search.spring.template.core.application.comment.command.dto.CommentRequest;
import com.rolandsall.elastic.search.spring.template.core.application.post.query.IPostQueryService;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCommandService implements ICommentCommandService {

    private final IPostQueryService postQueryService;

    @Override
    public void addComment(String postId, CommentRequest request) {
        Post post = postQueryService.findById(postId);
    }
}
