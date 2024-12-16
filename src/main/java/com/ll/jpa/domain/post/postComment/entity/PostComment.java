package com.ll.jpa.domain.post.postComment.entity;

import com.ll.jpa.domain.member.member.emtity.Member;
import com.ll.jpa.domain.post.post.entity.Post;
import com.ll.jpa.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostComment extends BaseTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String content;

    private boolean blind;
}
