package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.board.BoardSaveDTO;

import java.io.IOException;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;
}
