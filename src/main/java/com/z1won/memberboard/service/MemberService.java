package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.member.MemberDetailDTO;
import com.z1won.memberboard.dto.member.MemberLoginDTO;
import com.z1won.memberboard.dto.member.MemberSaveDTO;
import com.z1won.memberboard.dto.member.MemberUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException ;

    boolean login(MemberLoginDTO memberLoginDTO);

    MemberDetailDTO findById(Long memberId);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findByMemberEmail(String memberEmail);


    Long update(MemberUpdateDTO memberUpdateDTO) throws IOException;
}
