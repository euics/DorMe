package sejong.dormitory.domain.member;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import sejong.dormitory.global.constant.Role;
import sejong.dormitory.domain.board.Board;
import sejong.dormitory.domain.board.comment.BoardComment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        String password = passwordEncoder.encode(memberFormDto.getPassword1());

        member.setUsername(memberFormDto.getUsername());
        member.setPassword(password);
        member.setNickname(memberFormDto.getNickname());
        member.setLocal(memberFormDto.getLocal());
        member.setSchool(memberFormDto.getSchool());
        member.setRole(Role.USER);

        return member;
    }
}
