package com.z1won.memberboard.dto.member;

import com.z1won.memberboard.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;

    private MultipartFile memberFile;
    private String memberFilename; // 이게 파일을 받아주는 클래스 필드


}
