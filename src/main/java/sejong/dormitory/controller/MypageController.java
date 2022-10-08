package sejong.dormitory.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.dormitory.entity.Member;
import sejong.dormitory.service.MemberService;

@Controller
@RequestMapping("/mypage")
@AllArgsConstructor
public class MypageController {

    private final MemberService memberService;

    @GetMapping("")
    public String mypage(Model model,
                         Authentication authentication){
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e){
            return "redirect:/members/login";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);
        model.addAttribute("member",member);
        return "mypage/Mypage";
    }

    @GetMapping("/unRegister")
    public String unRegister(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);
        memberService.deleteByMember(member);
        return "redirect:/";
    }
}
