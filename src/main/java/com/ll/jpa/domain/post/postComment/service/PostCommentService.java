package com.ll.jpa.domain.post.postComment.service;

import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.domain.post.postComment.entity.PostComment;
import com.ll.jpa.domain.post.postComment.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;

    public PostComment write(Post post, String content) {
        PostComment postcomment = PostComment.builder()
                .post(post)
                .content(content)
                .build();

        return postCommentRepository.save(postcomment);
    }

    public Optional<PostComment> findById(long id) {
        return postCommentRepository.findById(id);
    }

    public void delete(PostComment postComment) {
        postCommentRepository.delete(postComment);
    }
}
