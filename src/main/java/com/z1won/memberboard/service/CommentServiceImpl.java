package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.comment.CommentDetailDTO;
import com.z1won.memberboard.dto.comment.CommentSaveDTO;
import com.z1won.memberboard.entity.BoardEntity;
import com.z1won.memberboard.entity.CommentEntity;
import com.z1won.memberboard.entity.MemberEntity;
import com.z1won.memberboard.repository.BoardRepository;
import com.z1won.memberboard.repository.CommentRepository;
import com.z1won.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    public final CommentRepository cr;
    public final BoardRepository br;
    public final MemberRepository mr;

    @Override
    public Long save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        MemberEntity memberEntity = mr.findByMemberEmail(commentSaveDTO.getCommentWriter());
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentSaveDTO, boardEntity, memberEntity);
        return cr.save(commentEntity).getId();
    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity = br.findById(boardId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();

        List<CommentDetailDTO> commentList = new ArrayList<>();
        for(CommentEntity ce : commentEntityList) {
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.toCommentDetailDTO(ce);
            commentList.add(commentDetailDTO);
        }
        return commentList;
    }

    @Override
    public void deleteById(Long commentId) {
        System.out.println("CommentServiceImpl.deleteById");
        cr.deleteById(commentId);
    }
}
