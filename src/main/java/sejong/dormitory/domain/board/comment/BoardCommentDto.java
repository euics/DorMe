package sejong.dormitory.domain.board.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.dormitory.domain.board.Board;
import sejong.dormitory.domain.member.Member;

@Getter
@NoArgsConstructor
public class BoardCommentDto{
    private String content;

    private Board board;
    private Member member;

    @Builder
    public BoardCommentDto(String content, Board board, Member member) {
        this.content = content;
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
                .member(member)
                .board(board)
                .build();

    }
}
