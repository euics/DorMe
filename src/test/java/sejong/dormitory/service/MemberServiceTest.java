package sejong.dormitory.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.dto.MemberFormDto;
import sejong.dormitory.entity.Member;
import sejong.dormitory.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    public MemberFormDto createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setUsername("euics");
        memberFormDto.setPassword1("1234");
        memberFormDto.setPassword2("1234");

        return memberFormDto;
    }

    public MemberFormDto createDifferentPasswordMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setUsername("euics");
        memberFormDto.setPassword1("1234");
        memberFormDto.setPassword2("5678");

        return memberFormDto;
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        MemberFormDto memberFormDto = createMember();
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        Member savedMember = memberRepository.save(member);
        assertThat(savedMember.getUsername()).isEqualTo(memberFormDto.getUsername());
    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateTest(){
        MemberFormDto memberFormDto1 = createMember();
        MemberFormDto memberFormDto2 = createMember();
        Member member1 = Member.createMember(memberFormDto1, passwordEncoder);
        Member member2 = Member.createMember(memberFormDto2, passwordEncoder);
        memberRepository.save(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.validateDuplicateIDPassword(memberFormDto2);
        });

        assertEquals(e.getMessage(), "이미 가입된 회원입니다.");
    }

    @Test
    @DisplayName("비밀번호 중복 테스트")
    public void passwordDuplicateTest(){
        MemberFormDto memberFormDto = createDifferentPasswordMember();

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.validateDuplicateIDPassword(memberFormDto);
        });

        assertEquals(e.getMessage(), "비밀번호가 일치하지 않습니다.");
    }
}