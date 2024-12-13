package com.ll.jpa.global.initData;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.postComment.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final PostService postService;
    private final PostCommentService postCommentService;
    @Autowired
    @Lazy
    private BaseInitData self;

    @Bean
    public ApplicationRunner baseInitData1ApplicationRunner() {
        return args -> {
            self.work1();
            self.work2();
            self.work3();
        };
    }

    @Transactional
    public void work1() {
        if (postService.count() > 0) return;

        Post post1 = postService.write("title1", "content1");
        Post post2 = postService.write("title2", "content2");
        Post post3 = postService.write("title3", "content3");

        post1.addComment(
                "comment1"
        );

        post1.addComment(
                "comment2"
        );

        post2.addComment(
                "comment3"
        );
    }

    @Transactional
    public void work2() {
        Post post1 = postService.findById(1).get();
        post1.setContent("content1-" + Math.random() * 100);

        PostComment firstCommentOfPost = post1.getComments().get(0);
        firstCommentOfPost.setContent("comment1-" + Math.random() * 100);

    }

    @Transactional
    public void work3() {

    }
}
