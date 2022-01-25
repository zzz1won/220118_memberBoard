package com.z1won.memberboard.service;

import com.z1won.memberboard.common.PagingConst;
import com.z1won.memberboard.dto.board.BoardDetailDTO;
import com.z1won.memberboard.dto.board.BoardPageingDTO;
import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.dto.board.BoardUpdateDTO;
import com.z1won.memberboard.entity.BoardEntity;
import com.z1won.memberboard.entity.MemberEntity;
import com.z1won.memberboard.repository.BoardRepository;
import com.z1won.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        MultipartFile boardFile = boardSaveDTO.getBoardFile();
        String boardFilename = boardFile.getOriginalFilename();
        boardFilename = System.currentTimeMillis() + "-" + boardFilename;

        System.out.println("BoardServiceImpl.save");
        /*System.out.println("BoardServiceImpl.filename" + boardFilename);*/
        String savePath = "C:\\Users\\exo_g\\Documents\\SpringBoot\\memberBoard\\src\\main\\resources\\static\\upload\\" + boardFilename;
        if (!boardFile.isEmpty())   {
        boardFile.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFileName(boardFilename);

        MemberEntity memberEntity = mr.findByMemberEmail(boardSaveDTO.getBoardWriter());
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardSaveDTO, memberEntity);
        return br.save(boardEntity).getId();
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);

        BoardDetailDTO boardDetailDTO = null;
            if (optionalBoardEntity.isPresent())    {
                BoardEntity boardEntity = optionalBoardEntity.get();
                boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
            }
        System.out.println("BoardServiceImpl.findById");
        return boardDetailDTO;
    }

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = br.findAll();
        List<BoardDetailDTO> boardList = new ArrayList<>();
        for (BoardEntity be : boardEntityList)  {
            boardList.add(BoardDetailDTO.toBoardDetailDTO(be));
        }
        System.out.println("BoardServiceImpl.findAll");
        return boardList;
    }

    @Override
    public void update(BoardUpdateDTO boardUpdateDTO) throws IOException {{
        MultipartFile boardFile = boardUpdateDTO.getBoardFile();
        String boardFilename = boardFile.getOriginalFilename();
        boardFilename = System.currentTimeMillis() + "-" +boardFilename;

        String updatePath = "C:\\Users\\exo_g\\Documents\\SpringBoot\\memberBoard\\src\\main\\resources\\static\\upload\\" + boardFilename;

        if (!boardFile.isEmpty()) {
            boardFile.transferTo(new File(updatePath));
        }

        MemberEntity memberEntity = mr.findByMemberEmail(boardUpdateDTO.getBoardWriter());
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardUpdateDTO, memberEntity);

        System.out.println("BoardServiceImpl.update");
        br.save(boardEntity);
    }

    }

    @Override
    public Page<BoardPageingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1)? 0:(page - 1);
        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardPageingDTO> boardList = boardEntities.map (
                board -> new BoardPageingDTO(board.getId(),
                        board.getMemberEmail(),
                        board.getBoardTitle())
        );
        return boardList;
    }

    @Override
    public void deleteById(Long boardId) {
        System.out.println("BoardServiceImpl.deleteById");
        br.deleteById(boardId);
    }

    @Override
    public List<BoardDetailDTO> search(String searchType, String keyword) {
        List<BoardEntity> searchList = new ArrayList<>();
        if (searchType.equals("title")) {
            searchList = br.findByBoardTitleContaining(keyword);
        }   else if (searchType.equals("writer")){
            searchList = br.findByMemberEmailContaining(keyword);
        }   else    {
            searchList = br.findByBoardContentsContaining(keyword);
        }

        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity be: searchList)    {
            boardDetailDTOList.add(BoardDetailDTO.toBoardDetailDTO(be));
        }
        return boardDetailDTOList;
    }


}
