package sejong.dormitory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.Board;
import sejong.dormitory.entity.Member;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
    Page<Board> findByMember(Member member, Pageable pageable);
}
