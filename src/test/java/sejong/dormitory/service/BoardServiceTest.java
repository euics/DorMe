package sejong.dormitory.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import sejong.dormitory.dto.BoardDto;
import sejong.dormitory.entity.Board;
import sejong.dormitory.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    public void 초기화(){
        boardRepository.deleteAll();
    }

    @Test
    public void 게시물등록() throws Exception {
        //given
        BoardDto boardDto = BoardDto.builder()
                .title("title")
                .content("content")
                .countVisit(1L)
                .build();

        List<MultipartFile> files = new ArrayList<>();
        boardService.createPost(boardDto,files);

        //when
        Board board = boardService.findById(1L);

        //then
        assertThat(board.getTitle()).isEqualTo("title");
        assertThat(board.getContent()).isEqualTo("content");
        assertThat(board.getCreatedBy()).isEqualTo("nickName");
        assertThat(board.getCountVisit()).isEqualTo(1L);
    }

    @Test
    public void 게시물수정() throws Exception{
        //given
        BoardDto boardDto = BoardDto.builder()
                .title("title")
                .content("content")
                .countVisit(1L)
                .build();

        List<MultipartFile> files = new ArrayList<>();
        boardService.createPost(boardDto,files);

        //when
        BoardDto boardDto1 = BoardDto.builder()
                .title("title2")
                .content("content2")
                .countVisit(1L)
                .build();

        boardService.updatePost(1L,boardDto1,files);
        Board board = boardService.findById(1L);

        //then
        assertThat(board.getTitle()).isEqualTo("title2");
        assertThat(board.getContent()).isEqualTo("content2");
        assertThat(board.getCreatedBy()).isEqualTo("nickName");
        assertThat(board.getCountVisit()).isEqualTo(1L);

    }

    @Test
    public void 게시물삭제() throws Exception{
        //given
        BoardDto boardDto = BoardDto.builder()
                .title("title")
                .content("content")
                .countVisit(1L)
                .build();

        List<MultipartFile> files = new ArrayList<>();
        boardService.createPost(boardDto,files);

        //when

        //then
        boardService.deletePost(1L);
    }
}
