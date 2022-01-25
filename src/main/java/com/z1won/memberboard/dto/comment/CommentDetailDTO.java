package com.z1won.memberboard.dto.comment;

import com.z1won.memberboard.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailDTO {

    private Long commentId;
    private Long boardId;
    private String commentWriter;
    private String commentContents;


    public static CommentDetailDTO toCommentDetailDTO (CommentEntity commentEntity) {
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setCommentId(commentEntity.getId());
        commentDetailDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDetailDTO.setCommentWriter(commentEntity.getMemberEntity().getMemberEmail());
        commentDetailDTO.setCommentContents(commentEntity.getCommentContents());

        return commentDetailDTO;

        // entity 객체를 detail로 가져오기 위해...
        // static : 정적인... service에서 불러오기 위해...
        // public static
    }
}
