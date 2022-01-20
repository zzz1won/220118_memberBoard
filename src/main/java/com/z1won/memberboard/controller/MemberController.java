package com.z1won.memberboard.controller;

import com.z1won.memberboard.dto.member.MemberDetailDTO;
import com.z1won.memberboard.dto.member.MemberLoginDTO;
import com.z1won.memberboard.dto.member.MemberSaveDTO;
import com.z1won.memberboard.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.z1won.memberboard.common.SessionConst.LOGIN_EMAIL;

@Controller
@RequiredArgsConstructor
@RequestMapping ("/member")
public class MemberController {
    private final MemberService ms;

    //0119
    @GetMapping ("/save")
    public String saveForm()    {
        System.out.println("MemberController.saveForm");
        return "/member/save";
    }

    @PostMapping ("/save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) throws IOException {
        System.out.println("MemberController.save");
        Long memberId = ms.save(memberSaveDTO);
        //?
        return "/member/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        System.out.println("MemberController.login");
        return "/member/login";
    }

    @PostMapping ("/login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session) {
        System.out.println("MemberController.login");
        //로그인 코드 공부, 분석 할 것

        boolean loginResult = ms.login(memberLoginDTO);
            if (loginResult)    {
                /*session.setAttribute("loginEmail",memberLoginDTO.getMemberEmail());*/
                session.setAttribute(LOGIN_EMAIL,memberLoginDTO.getMemberEmail());
                String redirectURL = (String) session.getAttribute("redirectURL");
                    if(redirectURL != null) {
                        return "redirect:/" + redirectURL;
                    }   else    {
                        return "redirect:/";
                    }
            }   else    {
                return "/member/login";
            }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)   {
        System.out.println("MemberController.logout");
        session.invalidate();
        //세션을,무효화
        return "./index";
    }

    // 0120 findAll을 먼저 해야겠다!
    @GetMapping     //(/member/)
    public String findAll(Model model) {
        System.out.println("MemberController.findAll");
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList",memberList);
        return "/member/findAll";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model)   {

        System.out.println("MemberController.mypage");
        return "/member/mypage";
    }

    @GetMapping("/update/{memberEmail}")
    public String updateForm(@PathVariable String memberEmail, Model model)  {
        MemberDetailDTO member = ms.findByMemberEmail(memberEmail);
        System.out.println("MemberController.updateForm");
        System.out.println("memberEmail: "+memberEmail);
        model.addAttribute("member",member);
        return "/member/update";
    }





}
