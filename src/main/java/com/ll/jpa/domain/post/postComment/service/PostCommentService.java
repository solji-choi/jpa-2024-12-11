package com.ll.jpa.domain.post.postComment.service;

import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.postComment.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;

    public PostComment write(long postId, String content) {
        PostComment postcomment = PostComment.builder()
                .postId(postId)
                .content(content)
                .build();

        return postCommentRepository.save(postcomment);
    }
}
