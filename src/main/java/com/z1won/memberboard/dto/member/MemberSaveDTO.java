package com.z1won.memberboard.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveDTO {

    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;

    private MultipartFile memberFile; // 이게 파일을 받아주는 클래스 필드
    private String memberFilename; // 이거는 파일 이름을 만들어서 Entity로 보내는 필드




}
