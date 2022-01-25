package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.board.BoardDetailDTO;
import com.z1won.memberboard.dto.board.BoardPageingDTO;
import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.dto.board.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;


    BoardDetailDTO findById(Long boardId);

    List<BoardDetailDTO> findAll();

    void update(BoardUpdateDTO boardUpdateDTO) throws IOException;

    Page<BoardPageingDTO> paging(Pageable pageable);

    void deleteById(Long boardId);

    List<BoardDetailDTO> search(String searchType, String keyword);
}
