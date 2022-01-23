package com.z1won.memberboard.service;

import com.z1won.memberboard.dto.member.MemberDetailDTO;
import com.z1won.memberboard.dto.member.MemberLoginDTO;
import com.z1won.memberboard.dto.member.MemberSaveDTO;
import com.z1won.memberboard.dto.member.MemberUpdateDTO;
import com.z1won.memberboard.entity.BoardEntity;
import com.z1won.memberboard.entity.MemberEntity;
import com.z1won.memberboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository mr;

/*    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        System.out.println("MemberServiceImpl.save");
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberSaveDTO);

        MultipartFile memberFile = memberSaveDTO.getMemberFile();
        String memberFilename = memberFile.getOriginalFilename();
        memberFilename = System.currentTimeMillis()+"-"+memberFilename;

        System.out.println("memberServiceImpl.filename"+memberFilename);
        String savePath = "C:\\Users\\exo_g\\Documents\\SpringBoot\\memberBoard\\src\\main\\resources\\static\\upload\\" + memberFilename;
            if(!memberFile.isEmpty())   {
                memberFile.transferTo(new File(savePath));
            }
            memberSaveDTO.setMemberFilename(memberFilename);
        return mr.save(memberEntity).getId();
    } */

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        MultipartFile memberFile = memberSaveDTO.getMemberFile();
        String memberFilename = memberFile.getOriginalFilename();
        memberFilename = System.currentTimeMillis() + "-" + memberFilename;

        System.out.println("MemberServiceImpl.save");

        System.out.println("memberServiceImpl.filename" + memberFilename);
        String savePath = "C:\\Users\\exo_g\\Documents\\SpringBoot\\memberBoard\\src\\main\\resources\\static\\upload\\" + memberFilename;
        if (!memberFile.isEmpty()) {
            memberFile.transferTo(new File(savePath));
        }
        memberSaveDTO.setMemberFilename(memberFilename);

        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberSaveDTO);
        return mr.save(memberEntity).getId();
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        System.out.println("MemberServiceImpl.login");
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        //email 값을 찾아와야 하니까 mr(entity에서 find 하는 코드 호출)

        if (memberEntity != null) {
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        Optional<MemberEntity> optionalMemberEntity = mr.findById(memberId);

        MemberDetailDTO memberDetailDTO = null;
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);
        }
        return memberDetailDTO;
    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for (MemberEntity me : memberEntityList) {
            memberList.add(MemberDetailDTO.toMemberDetailDTO(me));
        }
        System.out.println("MemberServiceImpl.findAll");
        return memberList;
    }

    @Override
    public MemberDetailDTO findByMemberEmail(String memberEmail) {
        System.out.println("MemberServiceImpl.findByMemberEmail");
        return MemberDetailDTO.toMemberDetailDTO(mr.findByMemberEmail(memberEmail));
    }

    @Override
    public Long update(MemberUpdateDTO memberUpdateDTO) throws IOException {
        MultipartFile memberFile = memberUpdateDTO.getMemberFile();
        String memberFilename = memberFile.getOriginalFilename();
        memberFilename = System.currentTimeMillis() + "-" + memberFilename;

        String updatePath = "C:\\Users\\exo_g\\Documents\\SpringBoot\\memberBoard\\src\\main\\resources\\static\\upload\\" + memberFilename;
        if (!memberFile.isEmpty()) {
            memberFile.transferTo(new File(updatePath));
        }
        memberUpdateDTO.setMemberFilename(memberFilename);
        System.out.println("MemberServiceImpl.update");
        return mr.save(MemberEntity.toUpdateMember(memberUpdateDTO)).getId();
    }


}
