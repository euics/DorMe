package sejong.dormitory.repository.dormitoryPage;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.dormitoryPage.GangwonDobong;
import sejong.dormitory.entity.dormitoryPage.GangwonGwanak;

public interface GangwonGwanakRepository extends JpaRepository<GangwonGwanak,Long> {
    GangwonGwanak findTopByOrderByIdDesc();
}
