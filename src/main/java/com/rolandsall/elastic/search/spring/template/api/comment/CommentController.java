package com.rolandsall.elastic.search.spring.template.api.comment;

import com.rolandsall.elastic.search.spring.template.core.application.comment.command.ICommentCommandService;
import com.rolandsall.elastic.search.spring.template.core.application.comment.command.dto.CommentRequest;
import com.rolandsall.elastic.search.spring.template.core.application.exceptions.ElasticQueryClientException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final ICommentCommandService commentCommandService;


    @PostMapping("/{postId}/comment")
    public ResponseEntity<Void> addComment(@PathVariable String postId, @RequestBody CommentApiRequest commentApiRequest) throws ElasticQueryClientException {
        commentCommandService.addComment(postId, buildRequestFrom(commentApiRequest));
        return ResponseEntity.noContent().build();
    }

    private CommentRequest buildRequestFrom(CommentApiRequest request) {
        return CommentRequest.builder()
                .ownerId(request.getOwnerId())
                .content(request.getContent())
                .build();
    }
}
