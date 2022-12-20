package sejong.dormitory.domain.map.dormitoryPageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.domain.map.dormitoryPageEntity.SejongDormitory;

public interface SejongDormitoryRepository extends JpaRepository<SejongDormitory,Long> {
    SejongDormitory findTopByOrderByIdDesc();
}
