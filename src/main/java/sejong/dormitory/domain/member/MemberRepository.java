package sejong.dormitory.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.dormitory.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);
}
