package sejong.dormitory.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import sejong.dormitory.entity.Board;
import sejong.dormitory.entity.Member;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private String dateTime;
    private String createdBy;
    private Long countVisit;
    private Member member;
    private List<MultipartFile> files;

    @Builder
    public BoardDto(Long id, String title, String content, String dateTime,
                    String createdBy, Long countVisit, Member member, List<MultipartFile> files) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.createdBy = createdBy;
        this.countVisit = countVisit;
        this.files = files;
    }


    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .dateTime(dateTime)
                .createdBy(createdBy)
                .countVisit(countVisit)
                .build();
    }

}
