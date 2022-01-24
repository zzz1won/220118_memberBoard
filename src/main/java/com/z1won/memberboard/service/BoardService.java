package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.board.BoardDetailDTO;
import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.dto.board.BoardUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;


    BoardDetailDTO findById(Long boardId);

    List<BoardDetailDTO> findAll();

    Long update(BoardUpdateDTO boardUpdateDTO) throws IOException;

}
