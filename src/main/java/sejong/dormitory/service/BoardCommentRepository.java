package sejong.dormitory.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
