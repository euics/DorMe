package sejong.dormitory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.dto.BoardCommentDto;
import sejong.dormitory.entity.BoardComment;
import sejong.dormitory.repository.BoardCommentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentService {

    private final BoardCommentRepository boardCommentRepository;

    @Transactional
    public void saveBoardComment(BoardCommentDto dto){
        boardCommentRepository.save(dto.toEntity());
    }

    @Transactional
    public void updateComment(Long id, BoardCommentDto boardCommentDto){

        BoardComment boardComment = boardCommentRepository.findById(id).orElseThrow((() ->
                new IllegalStateException("해당 댓글이 존재하지 않습니다.")));
        String created_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        boardComment.update(boardCommentDto.getContent(),created_date);
    }

    @Transactional
    public void deleteBoardComment(Long id){
        boardCommentRepository.deleteById(id);
    }

    public List<BoardComment> findBoardCommentsByBoardId(Long id){
        return boardCommentRepository.findBoardCommentsByBoardId(id);
    }

    public BoardComment findById(Long id){
        return boardCommentRepository.findById(id).get();
    }
}
