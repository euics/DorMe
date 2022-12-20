package sejong.dormitory.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import sejong.dormitory.domain.member.Member;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private Long countVisit;
    private Member member;
    private List<MultipartFile> files;

    @Builder
    public BoardDto(Long id, String title, String content,
                    Long countVisit, Member member, List<MultipartFile> files) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.countVisit = countVisit;
        this.files = files;
    }


    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .countVisit(countVisit)
                .build();
    }

}
