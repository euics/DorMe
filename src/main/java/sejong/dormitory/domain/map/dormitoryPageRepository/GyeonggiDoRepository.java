package sejong.dormitory.domain.map.dormitoryPageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.domain.map.dormitoryPageEntity.GyeonggiDo;

public interface GyeonggiDoRepository extends JpaRepository<GyeonggiDo,Long> {
    GyeonggiDo findTopByOrderByIdDesc();
}
