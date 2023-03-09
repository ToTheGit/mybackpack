package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.MemberFormDto;
import kr.ac.gachon.shop.entity.Member;
import kr.ac.gachon.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }
    //멤버서비스, 패스워드인코더 -> 메소드로 생성자를 자동으로 만들어서 주입
    //필드주입방법 주로 쓰는데 추천하지는 않는다
    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){ //에러걸렸냐
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);//dto를 엔티티로 바꾸는
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "main";
    }
    @GetMapping(value = "/login")
    public String loginMember(){
        return "member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/memberLoginForm";
    }
    @GetMapping(value = "/mypage")
    public String memberPage(Member member, Model model){
        model.addAttribute("member", member);
        return "member/myPage";
    }
    @GetMapping(value = "/logout")
    public String logoutMember(Model model){
        return "/";
    }
}