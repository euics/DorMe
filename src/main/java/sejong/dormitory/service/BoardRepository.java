package sejong.dormitory.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
