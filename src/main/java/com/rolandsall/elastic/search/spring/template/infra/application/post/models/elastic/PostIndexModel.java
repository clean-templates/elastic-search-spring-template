package com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@Document(indexName = "#{@elasticConfigData.indexName}")
public class PostIndexModel implements IndexModel{

    @JsonProperty
    private String id;

    @Field(type = FieldType.Keyword)
    private String owner;

    @Field(type = FieldType.Text)
    private List<String> topic;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ssZ")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty
    private ZonedDateTime createdAt;

    @Field(type = FieldType.Nested)
    private List<CommentIndexModel> replies;
}
