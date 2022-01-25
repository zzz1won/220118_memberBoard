package com.z1won.memberboard.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPagingDTO {

    private Long memberId;
    private String memberEmail;
    private String memberName;
}
