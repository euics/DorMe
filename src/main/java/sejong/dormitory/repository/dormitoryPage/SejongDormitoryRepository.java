package sejong.dormitory.repository.dormitoryPage;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.dormitoryPage.SejongDormitory;

public interface SejongDormitoryRepository extends JpaRepository<SejongDormitory,Long> {
    SejongDormitory findTopByOrderByIdDesc();
}
