package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.member.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException ;

    boolean login(MemberLoginDTO memberLoginDTO);

    MemberDetailDTO findById(Long memberId);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findByMemberEmail(String memberEmail);


    Long update(MemberUpdateDTO memberUpdateDTO) throws IOException;

    Page<MemberPagingDTO> paging(Pageable pageable);
}
