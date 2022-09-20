package sejong.dormitory.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class MemberFormDto {
    @NotBlank(message = "아이디 입력은 필수입니다.")
    private String username;

    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}", message = "올바르지 않은 형식의 비밀번호입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인 입력은 필수입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}", message = "올바르지 않은 형식의 비밀번호입니다.")
    private String password2;

    @NotEmpty(message = "닉네임 입력은 필수입니다.")
    private String nickname;

    @NotEmpty(message = "지역 입력은 필수입니다.")
    private String local;

    @NotEmpty(message = "학교 입력은 필수입니다.")
    private String school;
}
