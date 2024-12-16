package com.ll.jpa.domain.member.member.emtity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Member {
    //long -> null 안됨, Long -> null 가능
    //JPA 엔티티 클래스 특성상 id 필드는 null이 가능하도록
    @Id //primary key
    @GeneratedValue(strategy = IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime modifiedAt;

    @Column(unique = true, length = 30)
    private String username;

    @Column(length = 100)
    private String password;

    private String nickname;
}