package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.comment.CommentDetailDTO;
import com.z1won.memberboard.dto.comment.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    Long save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);

    void deleteById(Long commentId);
}
