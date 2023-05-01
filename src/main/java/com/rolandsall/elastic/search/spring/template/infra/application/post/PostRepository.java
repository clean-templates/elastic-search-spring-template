package com.rolandsall.elastic.search.spring.template.infra.application.post;

import com.rolandsall.elastic.search.spring.template.config.elastic.ElasticConfigData;
import com.rolandsall.elastic.search.spring.template.core.application.exceptions.PostNotFoundException;
import com.rolandsall.elastic.search.spring.template.core.application.post.command.IPostCreator;
import com.rolandsall.elastic.search.spring.template.core.application.post.query.IPostProvider;
import com.rolandsall.elastic.search.spring.template.core.domain.Comment;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import com.rolandsall.elastic.search.spring.template.infra.application.elastic.ElasticIndexUtil;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.ModelMapper;
import com.rolandsall.elastic.search.spring.template.infra.application.post.models.elastic.PostIndexModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public void editPost(String postId, Comment comment) throws PostNotFoundException {
        Post post = findById(postId);
        post.getComments().add(comment);
        addPost(post);
    }

    @Override
    public List<Post> getAllPosts() {
        log.info("received query to retrieve all posts");
        Query query = new NativeSearchQueryBuilder()
                .withQuery(new BoolQueryBuilder()
                        .must(QueryBuilders.matchAllQuery()))
                .build();

        return extractSearchResults(query);
    }

    @Override
    public List<Post> getPostByTopic(List<String> topics) {
        log.info("received query to retrieve all posts with these topics {}", topics);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        for (String topic : topics) {
            queryBuilder.should(QueryBuilders.matchQuery("topic", topic)
                    .fuzziness(Fuzziness.AUTO)
                    .maxExpansions(10)
            );

        }
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return extractSearchResults(searchQuery);
    }

    @Override
    public Post findById(String postId) throws PostNotFoundException {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("id", postId));

        log.info("received query to retrieve post with id  {}", postId);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return extractSearchResult(postId, searchQuery);

    }

    private Post extractSearchResult(String postId, NativeSearchQuery searchQuery) throws PostNotFoundException {
        SearchHit<PostIndexModel> searchResult = elasticsearchOperations.searchOne(searchQuery, PostIndexModel.class,
                IndexCoordinates.of(elasticConfigData.getIndexName()));

        if (searchResult == null) {
            log.error("No document found at elasticsearch with id {}", postId);
            throw new PostNotFoundException("No document found at elasticsearch with id " + postId);
        }
        log.info("Document with id {} retrieved successfully", searchResult.getId());
        return modelMapper.ToDomainModel(searchResult.getContent());
    }

    private List<Post> extractSearchResults(Query query) {
        SearchHits<PostIndexModel> searchResult = elasticsearchOperations.search(query, PostIndexModel.class,
                IndexCoordinates.of(elasticConfigData.getIndexName()));

        log.info("{} number of documents retrieved successfully", searchResult.getTotalHits());
        List<PostIndexModel> posts = searchResult.get().map(SearchHit::getContent).collect(Collectors.toList());

        return modelMapper.ToDomainModel(posts);
    }
}
