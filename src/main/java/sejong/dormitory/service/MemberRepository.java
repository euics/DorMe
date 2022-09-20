package sejong.dormitory.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
