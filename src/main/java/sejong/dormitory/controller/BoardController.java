package sejong.dormitory.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sejong.dormitory.dto.BoardDto;
import sejong.dormitory.entity.Board;
import sejong.dormitory.entity.BoardComment;
import sejong.dormitory.entity.Member;
import sejong.dormitory.service.BoardCommentService;
import sejong.dormitory.service.BoardService;
import sejong.dormitory.service.MemberService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;

    @GetMapping("/")
    public String viewBoardList(Model model, @PageableDefault(size = 10) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String searchText,
                            Authentication authentication) {

        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Page<Board> boards = boardService.searchByTitleOrContent(searchText,searchText,pageable);

            //검색기능
            int startPage = Math.max(1, boards.getPageable().getPageNumber() - 1);
            int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 3);

            model.addAttribute("boards", boards);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
        } catch (Exception e){
            return "login/login";
        }
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String viewBoardForm(Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e){
            return "login/login";
        }
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String createBoard(@RequestParam(value="file", required=false) List<MultipartFile> files,
                              @RequestParam(value="title") String title,
                              @RequestParam(value="content") String content,
                              Authentication authentication)throws Exception{

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Member member = memberService.findByUsername(username);

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        BoardDto boardDto =
                BoardDto.builder()
                        .member(member)
                        .title(title)
                        .content(content)
                        .dateTime(dateTime)
                        .createdBy(member.getNickname())
                        .countVisit(1L)
                        .build();
        boardService.createBoard(boardDto, files);
        return "redirect:/boards/";
    }

    @GetMapping("/boardContent/{id}")
    public String viewBoardContent(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);

        Long countVisit = board.getCountVisit()+1L;
        boardService.updateCountVisit(board, countVisit);
        // System.out.println("= " + board.getCountVisit());

        board.updateVisit(countVisit);
        List<BoardComment> comments = boardCommentService.findBoardCommentsByBoardId(id);

        model.addAttribute("comments", comments);
        model.addAttribute(board);

        return "board/boardContent";
    }

    @GetMapping("/editPost/{id}")
    public String editPost(@PathVariable("id") Long id,
                           Authentication authentication)
            throws Exception{
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e){
            return "login/login";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);
        Board board = boardService.findById(id);

        if(!board.getCreatedBy().equals(member.getNickname()))
            throw new Exception("수정 권한이 없습니다.");

        boardService.deletePost(id);
        return "board/boardForm";
    }

    @PostMapping("/editPost/{id}")
    public String editPost(@PathVariable("id") Long id,
                           @RequestParam(value="file", required=false) List<MultipartFile> files,
                           @RequestParam(value="title") String title,
                           @RequestParam(value="content") String content
                           ) throws Exception{

        BoardDto boardDto =
                BoardDto.builder()
                        .title(title)
                        .content(content)
                        .countVisit(1L)
                        .build();

        boardService.updatePost(id,boardDto,files);
        return "redirect:/boards/";
    }

    @GetMapping("/postDelete/{id}")
    public String deletePost(@PathVariable("id") Long id, Authentication authentication)
            throws Exception{

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);
        Board board = boardService.findById(id);

        if(!board.getCreatedBy().equals(member.getNickname()))
            throw new Exception("삭제 권한이 없습니다.");
        boardService.deletePost(id);
        return "redirect:/boards/";
    }

}

