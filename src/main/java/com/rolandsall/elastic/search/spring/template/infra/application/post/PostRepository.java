package com.rolandsall.elastic.search.spring.template.infra.application.post;

import com.rolandsall.elastic.search.spring.template.config.elastic.ElasticConfigData;
import com.rolandsall.elastic.search.spring.template.core.application.post.command.IPostCreator;
import com.rolandsall.elastic.search.spring.template.core.application.post.query.IPostProvider;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import com.rolandsall.elastic.search.spring.template.infra.application.elastic.ElasticIndexUtil;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.ModelMapper;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic.PostIndexModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostRepository implements IPostProvider, IPostCreator {

    private final ModelMapper modelMapper;
    private final ElasticIndexUtil<PostIndexModel> elasticIndexUtil;
    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticConfigData elasticConfigData;

    @Override
    public void addPost(Post post) {
        log.info("received command to add: {}", post);
        PostIndexModel postIndexModel = modelMapper.ToPersistenceEntity(post);
        IndexQuery indexQuery = elasticIndexUtil.getIndexQuery(postIndexModel);
        String index = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(elasticConfigData.getIndexName()));
        log.info("document indexed successfully with type: {} and id: {}", PostIndexModel.class.getName(), index);
    }

    @Override
    public List<Post> getAllPosts() {
        log.info("received query to retrieve all posts");
        return null;
    }
}
