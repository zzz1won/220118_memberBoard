package com.z1won.memberboard.controller;

import com.z1won.memberboard.dto.board.BoardDetailDTO;
import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.dto.board.BoardUpdateDTO;
import com.z1won.memberboard.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bs;

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
    public String paging (@PageableDefault(page = 1) Pageable pageablem, Model model)
}
