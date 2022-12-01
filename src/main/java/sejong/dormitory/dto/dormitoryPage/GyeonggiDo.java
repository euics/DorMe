package sejong.dormitory.dto.dormitoryPage;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@Builder
public class GyeonggiDo {
    private String condition1;
    private String condition2;
    private String recruitPeriod;
    private String recruitMemberInfo;
    private String price;
    private String roomInfo;
    private String detailImagePath;
}
