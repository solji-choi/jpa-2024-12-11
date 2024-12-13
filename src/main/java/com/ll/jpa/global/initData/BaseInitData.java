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

import java.util.List;

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
        Post post = postService.findById(1).get();
        System.out.println("1번글 로드 완료");

        List<PostComment> postComments = post.getComments();
        System.out.println("1번글의 댓들들 로드 완료");
        
        PostComment postComment1 = postComments.get(0);
        System.out.println("1번글의 첫번째 댓글 로드 완료");

        PostComment postComment2 = postComments.get(1);
        System.out.println("1번글의 두번째 댓글 로드 완료");

    }

    @Transactional
    public void work3() {
        Post post1 = postService.findById(1).get();

        post1.getComments().size();

        post1.addComment("comment4"); // 이런경우에도 PERSIST 에 의해서 댓글이 등록됩니다.

        System.out.println( post1.getComments().get(2).getContent());
    }
}
