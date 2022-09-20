package sejong.dormitory.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;
import sejong.dormitory.dto.MemberFormDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Board> boardList = new ArrayList<>();
    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BoardComment> boardCommentList = new ArrayList<>();

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String nickname;

    private String local;

    private String school;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        String password = passwordEncoder.encode(memberFormDto.getPassword1());

        member.setUsername(memberFormDto.getUsername());
        member.setPassword(password);
        member.setNickname(memberFormDto.getNickname());
        member.setLocal(memberFormDto.getLocal());
        member.setSchool(memberFormDto.getSchool());

        return member;
    }
}
