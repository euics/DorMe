package sejong.dormitory.dto;

import lombok.Getter;
import sejong.dormitory.entity.Board;

import java.util.List;

@Getter
public class BoardResponseDto {
    private Long id;
    private String member;
    private String title;
    private String content;
    private List<Long> fileId;  // 첨부 파일 id 목록

    public BoardResponseDto(Board entity, List<Long> fileId) {
        this.id = entity.getId();
        this.member = entity.getMember().getNickname();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.fileId = fileId;
    }
}