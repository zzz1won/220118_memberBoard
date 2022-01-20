package com.z1won.memberboard.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {

    private String memberEmail;
    private String memberPassword;
}
