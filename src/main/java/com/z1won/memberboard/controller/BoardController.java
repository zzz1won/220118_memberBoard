package com.z1won.memberboard.controller;

import com.z1won.memberboard.dto.board.BoardSaveDTO;
import com.z1won.memberboard.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService bs;

@GetMapping
    public String saveForm()    {
    System.out.println("BoardController.saveForm");
    return "/board/save";
}

@PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO) throws IOException {
    System.out.println("BoardController.save");
    Long boardId = bs.save(boardSaveDTO);
    return "/board/";
}
}
