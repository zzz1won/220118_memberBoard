package com.z1won.memberboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity {
    //댓글 데이터: 댓글 번호, 게시글 번호, 작성자, 작성 시간, 수정 시간
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private String commentContents;

    // 코멘트 : 멤버 조인 n:1
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    // 코멘트 : 보드 조인 n:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
}


