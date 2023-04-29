package com.rolandsall.elastic.search.spring.template.api.post;

import com.rolandsall.elastic.search.spring.template.core.application.post.command.IPostCommandService;
import com.rolandsall.elastic.search.spring.template.core.application.post.command.dto.PostRequest;
import com.rolandsall.elastic.search.spring.template.core.application.post.query.IPostQueryService;
import com.rolandsall.elastic.search.spring.template.core.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostCommandService postCommandService;
    private final IPostQueryService postQueryService;

    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody PostApiRequest postApiRequest) {
        PostRequest postRequest = buildPostRequestFrom(postApiRequest);
        postCommandService.addPost(postRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        List<Post> posts = postQueryService.getAllPosts();
        return ResponseEntity.ok(posts);
    }



    private PostRequest buildPostRequestFrom(PostApiRequest postApiRequest) {
        return PostRequest.builder()
                .topics(postApiRequest.getTopic())
                .ownerId(postApiRequest.getOwnerId())
                .content(postApiRequest.getContent())
                .build();
    }
}
