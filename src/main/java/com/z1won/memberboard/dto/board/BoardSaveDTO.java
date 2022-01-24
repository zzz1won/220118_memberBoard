package com.z1won.memberboard.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {

    private String boardTitle;
    private String boardWriter;
    private String boardContents;

    private MultipartFile boardFile;
    private String boardFileName;

    private int hits;



}
