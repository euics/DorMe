package sejong.dormitory.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sejong.dormitory.dto.BoardCommentDto;
import sejong.dormitory.entity.BoardComment;
import sejong.dormitory.repository.BoardCommentRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardCommentServiceTest {

    @Autowired
    BoardCommentService boardCommentService;

    @Autowired
    BoardCommentRepository boardCommentRepository;

    @AfterEach
    public void 초기화(){
        boardCommentRepository.deleteAll();
    }
    @Test
    public void 댓글작성(){
        //given
        BoardCommentDto boardCommentDto1 = BoardCommentDto.builder()
                .content("content")
                .dateTime(LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .createdBy("name")
                .build();

        BoardCommentDto boardCommentDto2 = BoardCommentDto.builder()
                .content("content2")
                .dateTime(LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .createdBy("name2")
                .build();

        boardCommentService.saveBoardComment(boardCommentDto1);
        boardCommentService.saveBoardComment(boardCommentDto2);

        //when
        BoardComment boardComment1 = boardCommentService.findById(1L);
        BoardComment boardComment2 = boardCommentService.findById(2L);

        //then
        assertThat(boardComment1.getContent()).isEqualTo("content");
        assertThat(boardComment1.getCreatedBy()).isEqualTo("name");
        assertThat(boardComment2.getContent()).isEqualTo("content2");
        assertThat(boardComment2.getCreatedBy()).isEqualTo("name2");
    }

    @Test
    public void 댓글삭제(){
        //given
        BoardCommentDto boardCommentDto = BoardCommentDto.builder()
                .content("content")
                .dateTime(LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .createdBy("name")
                .build();

        boardCommentService.saveBoardComment(boardCommentDto);

        //when

        //then
        boardCommentService.deleteBoardComment(1L);
    }
}
