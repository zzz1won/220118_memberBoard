package com.z1won.memberboard.controller;

import com.z1won.memberboard.dto.comment.CommentDetailDTO;
import com.z1won.memberboard.dto.comment.CommentSaveDTO;
import com.z1won.memberboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService cs;


    @PostMapping("/save")
    public @ResponseBody List<CommentDetailDTO> save (@ModelAttribute CommentSaveDTO commentSaveDTO)    {
        cs.save(commentSaveDTO);
        List<CommentDetailDTO> commentList = cs.findAll(commentSaveDTO.getBoardId());
        return commentList;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteById2 (@PathVariable Long commentId)   {
        cs.deleteById(commentId);
        System.out.println("CommentController.deleteById");
        return new ResponseEntity (HttpStatus.OK);
    }


}
