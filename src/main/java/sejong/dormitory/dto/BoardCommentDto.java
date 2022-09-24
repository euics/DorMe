package sejong.dormitory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.dormitory.entity.Board;
import sejong.dormitory.entity.BoardComment;
import sejong.dormitory.entity.Member;

@Getter
@NoArgsConstructor
public class BoardCommentDto {
    private String content;
    private String dateTime;
    private String createdBy;
    private Board board;
    private Member member;

    @Builder
    public BoardCommentDto(String content, String dateTime, String createdBy, Board board, Member member) {
        this.content = content;
        this.dateTime = dateTime;
        this.createdBy = createdBy;
        if(this.board != null){
            board.getBoardCommentList().remove(this);
        }else
            this.board = board;
        if(this.member != null){
            member.getBoardCommentList().remove(this);
        }else
            this.member = member;
    }

    public BoardComment toEntity() {
        return BoardComment.builder()
                .content(content)
                .dateTime(dateTime)
                .createdBy(createdBy)
                .member(member)
                .board(board)
                .build();

    }
}
