package com.rolandsall.elastic.search.spring.template.runner;

import com.rolandsall.elastic.search.spring.template.core.application.post.command.IPostCommandService;
import com.rolandsall.elastic.search.spring.template.core.application.post.command.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

    private final IPostCommandService postCommandService;

    /***
     * Uncomment the below for initial data instead of creating post using post API
     */
    @Override
    public void run(String... args) throws Exception {
//        postCommandService.addPost(PostRequest.builder()
//                .ownerId("00000-0000-00000-00000")
//                .topics(List.of("DDD", "Architecture", "clean code", "modern software applications"))
//                .content("DDD is a software development technique that guarantees clean code in modern software app")
//                .build());
//
//
//        postCommandService.addPost(PostRequest.builder()
//                .ownerId("00000-1111-1111-00000")
//                .topics(List.of("clean code", "modern software apps"))
//                .content("Clean code is a must for modern software applications")
//                .build());

    }
}
