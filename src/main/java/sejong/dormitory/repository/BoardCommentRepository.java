package sejong.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
