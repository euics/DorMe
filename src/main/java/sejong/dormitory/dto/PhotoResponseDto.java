package sejong.dormitory.dto;

import lombok.Getter;
import sejong.dormitory.entity.Photo;

@Getter
public class PhotoResponseDto {
    private Long fileId;  // 파일 id

    public PhotoResponseDto(Photo entity){
        this.fileId = entity.getId();
    }
}
