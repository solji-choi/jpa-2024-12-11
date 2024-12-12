package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;

    @Bean
    public ApplicationRunner baseInitData1ApplicationRunner() {
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) throws Exception {
                if (postService.count() > 0) return;

                Post post1 = postService.write("title1", "content1");
                Post post2 = postService.write("title2", "content2");
                Post post3 = postService.write("title3", "content3");
            }
        };
    }
}
