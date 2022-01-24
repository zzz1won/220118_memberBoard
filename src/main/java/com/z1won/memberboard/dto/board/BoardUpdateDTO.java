package com.z1won.memberboard.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateDTO {

    private Long boardId;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;

    private MultipartFile boardFile;
    private String boardFilename;


}
