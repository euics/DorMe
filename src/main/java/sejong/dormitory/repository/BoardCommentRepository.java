package sejong.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.BoardComment;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findBoardCommentsByBoardId(Long boardId);
}
