package com.z1won.memberboard.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDTO {
    private Long commentId;
    private Long boardId;
    private String commentWriter;
    private String commentContents;
}
