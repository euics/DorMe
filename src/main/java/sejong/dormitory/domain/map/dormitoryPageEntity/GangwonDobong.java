package sejong.dormitory.domain.map.dormitoryPageEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.dormitory.global.baseEntity.BaseEntity;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GangwonDobong extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facility0;
    private String context0;
    private String imagePath0;
    private String detailImagePath0;

    private String facility1;
    private String context1;
    private String imagePath1;
    private String detailImagePath1;
}
