package com.ll.jpa.global.initData;

import com.ll.jpa.domain.member.member.emtity.Member;
import com.ll.jpa.domain.member.member.service.MemberService;
import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.post.service.PostService;
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
    private final MemberService memberService;
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
        };
    }

    @Transactional
    public void work1() {
        if (memberService.count() > 0) return;

        memberService.join("system", "1234", "시스템");
        memberService.join("admin", "1234", "관리자");
        memberService.join("user1", "1234", "회원1");
        memberService.join("user2", "1234", "회원2");
        memberService.join("user3", "1234", "회원3");
    }

    @Transactional
    public void work2() {
        if (postService.count() > 0) return;

        Member member1 = memberService.findByUsename("user1").get();
        Member member2 = memberService.findByUsename("user2").get();
        Member member3 = memberService.findByUsename("user3").get();

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
}
