package com.rolandsall.elastic.search.spring.template.core.application.comment.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private String content;
    private String ownerId;
}



