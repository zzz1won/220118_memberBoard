package com.z1won.memberboard.controller;

import com.z1won.memberboard.common.PagingConst;
import com.z1won.memberboard.dto.board.BoardDetailDTO;
import com.z1won.memberboard.dto.board.BoardPageingDTO;
import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.dto.board.BoardUpdateDTO;
import com.z1won.memberboard.dto.comment.CommentDetailDTO;
import com.z1won.memberboard.service.BoardService;
import com.z1won.memberboard.service.CommentService;
import com.z1won.memberboard.service.CommentServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bs;
    private final CommentService cs;

@GetMapping("/save")
    public String saveForm()    {
    System.out.println("BoardController.saveForm");
    return "/board/save";
}

@PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
    System.out.println("BoardController.save");
    Long boardId = bs.save(boardSaveDTO);
    /*return "/board/";*/
    return "redirect:/board/";
}

@GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model)   {
    System.out.println("BoardController.updateForm");
    BoardDetailDTO board = bs.findById(boardId);

    model.addAttribute("board", board);
    return "/board/update";
}

//update 처리기능 추가


@GetMapping("/")
    public String findAll(Model model)   {
    List<BoardDetailDTO> boardList = bs.findAll();
    model.addAttribute("boardList", boardList);
    System.out.println("BoardController.findAll");
    return "/board/findAll";
}

@GetMapping("/{boardId}")
    public String findById(@PathVariable Long boardId, Model model) {
    BoardDetailDTO board = bs.findById(boardId);
    List<CommentDetailDTO> commentDetailDTOList = cs.findAll(boardId);
    model.addAttribute("commentList",commentDetailDTOList);
    model.addAttribute("board",board);
    System.out.println("BoardController.findById");
    return "/board/findById";
}

@PutMapping("/{boardId}")
    public ResponseEntity update (@ModelAttribute BoardUpdateDTO boardUpdateDTO) throws IOException {
    System.out.println("BoardController.update");
    bs.update(boardUpdateDTO);
    return new ResponseEntity<>(HttpStatus.OK);
}


@GetMapping
    public String paging (@PageableDefault(page = 1) Pageable pageable, Model model)   {
        Page<BoardPageingDTO> boardList = bs.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber()/ PagingConst.BLOCK_LIMIT)))-1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT -1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        System.out.println("BoardController.paging");
        return "board/paging";
}

@DeleteMapping("/{boardId}")
    public ResponseEntity deleteById(@PathVariable Long boardId)    {
    bs.deleteById(boardId);
    System.out.println("BoardController.deleteById");
    return new ResponseEntity(HttpStatus.OK);
}

@GetMapping("/search")
    public String search(@RequestParam("searchType") String searchType,
                         @RequestParam("keyword") String keyword, Model model)  {
    List<BoardDetailDTO> searchList = bs.search(searchType, keyword);
    model.addAttribute("boardList", searchList);
    return "/board/findAll";
}

}
