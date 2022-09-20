package sejong.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
