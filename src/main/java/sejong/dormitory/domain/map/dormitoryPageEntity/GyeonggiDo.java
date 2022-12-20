package sejong.dormitory.domain.map.dormitoryPageEntity;

import lombok.*;
import sejong.dormitory.global.baseEntity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class GyeonggiDo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String condition1;
    private String condition2;
    private String recruitPeriod;
    private String recruitMemberInfo;
    private String price;
    private String roomInfo;
    private String detailImagePath;
}
