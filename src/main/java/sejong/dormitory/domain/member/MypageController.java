package sejong.dormitory.domain.member;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sejong.dormitory.domain.board.Board;
import sejong.dormitory.domain.member.Member;
import sejong.dormitory.domain.board.BoardService;
import sejong.dormitory.domain.member.MemberService;

@Controller
@RequestMapping("/mypage")
@AllArgsConstructor
public class MypageController {

    private final MemberService memberService;
    private final BoardService boardService;

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

    @GetMapping("/myPost")
    public String myPost(Model model, @PageableDefault(size = 10) Pageable pageable,
                         Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);

        Page<Board> boards= boardService.findByMember(member,pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getPageable().getPageNumber()+4, boards.getTotalPages());

        model.addAttribute("boards", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/postList";
    }
    @PostMapping("/unRegister")
    public String unRegister(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);
        memberService.deleteByMember(member);
        return "redirect:/";
    }
}
