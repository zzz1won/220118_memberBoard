package com.z1won.memberboard.entity;

import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.dto.board.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    //게시글 데이터: 글 번호, 제목, 작성자, 내용, 조회수, 첨부 파일명, 작성 시간, 수정 시간
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @NotNull
    @Column
    private String boardTitle;

    @NotNull
    @Column
    private String memberEmail;
    //멤버참조

    @NotNull
    @Column
    private String boardContents;

    @Column
    private Integer boardHits;

    @Column
    private String boardFilename;


    // 게시글 : 회원 join n:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    // 게시물 : 댓글 join 1:n
    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    // --------------------------------------------


    public static BoardEntity toSaveEntity (BoardSaveDTO boardSaveDTO, MemberEntity memberEntity)  {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardFilename(boardSaveDTO.getBoardFileName());
        boardEntity.setMemberEmail(boardSaveDTO.getBoardWriter());
        boardEntity.setMemberEntity(memberEntity);

        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardUpdateDTO boardUpdateDTO, MemberEntity memberEntity)  {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setMemberEmail(boardUpdateDTO.getBoardWriter());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        boardEntity.setBoardFilename(boardUpdateDTO.getBoardFilename());
        boardEntity.setMemberEntity(memberEntity);

        return boardEntity;
    }






}
