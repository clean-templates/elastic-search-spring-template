package com.rolandsall.elastic.search.spring.template.api.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentApiRequest {
    private String content;
    private String ownerId;
}
