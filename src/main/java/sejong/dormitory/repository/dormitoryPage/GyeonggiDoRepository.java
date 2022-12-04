package sejong.dormitory.repository.dormitoryPage;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.dormitoryPage.GyeonggiDo;

public interface GyeonggiDoRepository extends JpaRepository<GyeonggiDo,Long> {
    GyeonggiDo findTopByOrderByIdDesc();
}
