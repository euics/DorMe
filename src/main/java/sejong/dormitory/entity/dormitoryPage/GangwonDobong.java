package sejong.dormitory.entity.dormitoryPage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sejong.dormitory.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GangwonDobong extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recruitPeriod;
    private String condition1;
    private String condition2;
    private String joinPrice;
    private String dormitoryPrice;
    private String detailImagePath;
}
