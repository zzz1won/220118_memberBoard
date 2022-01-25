package com.z1won.memberboard.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPageingDTO {

    private Long boardId;
    private String boardWriter;
    private String boardTitle;
}
