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
import sejong.dormitory.entity.Photo;
import sejong.dormitory.service.BoardCommentService;
import sejong.dormitory.service.BoardService;
import sejong.dormitory.service.MemberService;
import sejong.dormitory.service.PhotoService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final BoardCommentService boardCommentService;
    private final PhotoService photoService;
    @GetMapping("")
    public String viewPostList(Model model, @PageableDefault(size = 10) Pageable pageable,
                            @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board> boards = boardService.findByTitleContainingOrContentContaining(searchText,searchText,pageable);

        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getPageable().getPageNumber()+4, boards.getTotalPages());

        model.addAttribute("boards", boards);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/postList";
    }

    @GetMapping("/createForm")
    public String viewPostForm(Authentication authentication) {
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e){
            return "redirect:/members/login";
        }
        return "board/postCreateForm";
    }

    @PostMapping("/createForm")
    public String createPost(@RequestParam(value="file", required=false) List<MultipartFile> files,
                              @RequestParam(value="title") String title,
                              @RequestParam(value="content") String content,
                              Authentication authentication)throws Exception{

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        Member member = memberService.findByUsername(username);


        BoardDto boardDto =
                BoardDto.builder()
                        .member(member)
                        .title(title)
                        .content(content)
                        .countVisit(1L)
                        .build();
        boardService.createPost(boardDto, files);
        return "redirect:/boards";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findById(id);

        Long countVisit = board.getCountVisit()+1L;
        boardService.updateCountVisit(board, countVisit);
        board.updateVisit(countVisit);

        List<BoardComment> comments = boardCommentService.findBoardCommentsByBoardId(id);
        List<Photo> photos = photoService.findAllByBoardId(id);

        model.addAttribute("photos",photos);
        model.addAttribute("comments", comments);
        model.addAttribute(board);

        return "board/viewPost";
    }

    @GetMapping("/editPost/{id}")
    public String editPost(@PathVariable("id") Long id,
                           Authentication authentication)
            throws Exception{
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        } catch (Exception e){
            return "redirect:/members/login";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Member member = memberService.findByUsername(username);
        Board board = boardService.findById(id);

        if(!board.getCreatedBy().equals(member.getUsername()))
            throw new Exception("수정 권한이 없습니다.");

        boardService.deletePost(id);
        return "board/postCreateForm";
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

        if(!board.getCreatedBy().equals(member.getUsername()))
            throw new Exception("삭제 권한이 없습니다.");
        boardService.deletePost(id);
        return "redirect:/boards/";
    }

}

