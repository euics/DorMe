package sejong.dormitory.domain.board.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.domain.board.comment.BoardComment;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findBoardCommentsByBoardId(Long id);
}
