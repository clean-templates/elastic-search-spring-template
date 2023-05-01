package com.rolandsall.elastic.search.spring.template.core.application.comment.command;

import com.rolandsall.elastic.search.spring.template.core.application.comment.command.dto.CommentRequest;
import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import com.rolandsall.elastic.search.spring.template.core.application.post.command.IPostCommandService;
import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostIdGenerator;
import com.rolandsall.elastic.search.spring.template.core.application.post.generator.PostTimeGenerator;
import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentCommandService implements ICommentCommandService {

    private final IPostCommandService postCommandService;
    private final PostIdGenerator postIdGenerator;
    private final PostTimeGenerator postTimeGenerator;


    @Override
    public void addComment(String postId, CommentRequest request) throws PostNotFoundException {
        Comment comment = buildCommentFrom(request);
        postCommandService.editPost(postId, comment);
    }

    private Comment buildCommentFrom(CommentRequest request) {
        return Comment.builder()
                .owner(Owner.builder().id(request.getOwnerId()).build())
                .id(postIdGenerator.generateId())
                .createdAt(postTimeGenerator.generateTime())
                .content(request.getContent())
                .build();
    }
}
