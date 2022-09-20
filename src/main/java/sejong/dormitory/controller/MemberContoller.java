package sejong.dormitory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.dormitory.dto.MemberFormDto;
import sejong.dormitory.service.MemberService;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberContoller {
    private final MemberService memberService;

    @GetMapping("/new")
    public String memberForm(@ModelAttribute("memberFormDto")MemberFormDto memberFormDto){
        return "member/memberForm";
    }
}
