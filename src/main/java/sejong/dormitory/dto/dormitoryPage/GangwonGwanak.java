package sejong.dormitory.dto.dormitoryPage;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GangwonGwanak {
    private String recruitPeriod;
    private List<String> recruitMember;
    private String condition1;
    private String condition2;
    private String joinPrice;
    private String dormitoryPrice;
    private String detailImagePath;
}
