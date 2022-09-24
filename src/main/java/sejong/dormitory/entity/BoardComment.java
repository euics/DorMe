package sejong.dormitory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardComment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardComment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String content;
    private String dateTime;
    private String createdBy;

    @Builder
    public BoardComment(String content, String dateTime,
                        String createdBy, Board board, Member member) {
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

    public void update(String content, String dateTime){
        this.content = content;
        this.dateTime = dateTime;
    }

}
