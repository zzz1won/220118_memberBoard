package com.z1won.memberboard.dto.board;

import com.z1won.memberboard.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private String boardTitle;
    private String memberEmail;
    private String boardContents;
    private String boardFilename;
    private int hits;

    public static BoardDetailDTO toBoardDetailDTO (BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setMemberEmail(boardEntity.getMemberEmail());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardFilename(boardEntity.getBoardFilename());
        /*boardDetailDTO.setHits(boardEntity.getHits());*/

        return boardDetailDTO;
    }


}
