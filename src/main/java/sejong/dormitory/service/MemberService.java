package sejong.dormitory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.dto.MemberFormDto;
import sejong.dormitory.entity.Member;
import sejong.dormitory.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public void validateDuplicateIDPassword(MemberFormDto memberFormDto) {
        Member findMember = memberRepository.findByUsername(memberFormDto.getUsername());
        if(findMember != null)
            throw new IllegalStateException("이미 가입된 회원입니다.");
        if(!memberFormDto.getPassword1().equals(memberFormDto.getPassword2()))
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);

        if(member == null)
            throw new UsernameNotFoundException(username);

        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .build();
    }

    public Member findByUsername(String username){
        return memberRepository.findByUsername(username);
    }
}
