package sejong.dormitory.dto.dormitoryPage;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@Builder
public class SejongDormitory {
    private String condition1;
    private String condition2;
    private String price;
    private String detailImagePath;

}
