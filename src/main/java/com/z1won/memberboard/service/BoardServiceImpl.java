package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.entity.BoardEntity;
import com.z1won.memberboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        MultipartFile boardFile = boardSaveDTO.getBoardFile();
        String boardFilename = boardFile.getOriginalFilename();
        boardFilename = System.currentTimeMillis() + "-" + boardFilename;

        System.out.println("BoardServiceImpl.save");

        return null;
    }
}
