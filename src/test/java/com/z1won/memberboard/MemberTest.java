package com.z1won.memberboard;

import com.z1won.memberboard.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService ms;

    @Test
    @DisplayName("회원가입 TEST")
    public void joinTest()  {

    }
}
