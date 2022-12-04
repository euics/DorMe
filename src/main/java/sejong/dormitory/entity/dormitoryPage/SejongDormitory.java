package sejong.dormitory.entity.dormitoryPage;

import lombok.*;
import sejong.dormitory.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class SejongDormitory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String condition1;
    private String condition2;
    private String price;
    private String period1;
    private String period2;
    private String detailImagePath;

}
