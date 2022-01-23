package com.z1won.memberboard.entity;

import com.z1won.memberboard.dto.member.MemberSaveDTO;
import com.z1won.memberboard.dto.member.MemberUpdateDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity extends BaseEntity {
    // 주어진 자료:
    // 회원 데이터: 회원 번호, 이메일, 비밀번호, 이름, 전화번호, 프로필 사진, 가입 일자, 수정 일자

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @NotNull
    @Column(nullable = false)
    private String memberPassword;

    @NotNull
    @Column(nullable = false)
    private String memberName;

    @Column(nullable = true)
    private String memberPhone;

    @Column
    private String memberFilename;

    /*@Column
    private String memberFileRoute; */  //file은 db에 저장하지 않으니까!

    // 가입일 수정일은 BaseEntity에서 도와준다.
    // 그럼 파일도 그렇게 되나?

    // 멤버 : 게시물 조인 1:n
    @OneToMany (mappedBy = "memberEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // 멤버 : 코멘트 조인 1:n
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    // -----------

    public static MemberEntity toSaveEntity (MemberSaveDTO memberSaveDTO)   {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberPhone(memberSaveDTO.getMemberPhone());
        memberEntity.setMemberFilename(memberSaveDTO.getMemberFilename());

        return memberEntity;
    }

    public static MemberEntity toUpdateMember (MemberUpdateDTO memberUpdateDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberUpdateDTO.getMemberId());
        memberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberUpdateDTO.getMemberPassword());
        memberEntity.setMemberName(memberUpdateDTO.getMemberName());
        memberEntity.setMemberPhone(memberUpdateDTO.getMemberPhone());
        memberEntity.setMemberFilename(memberUpdateDTO.getMemberFilename());

        return memberEntity;
    }

}
