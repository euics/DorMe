package sejong.dormitory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sejong.dormitory.dto.PhotoDto;
import sejong.dormitory.entity.Photo;
import sejong.dormitory.repository.PhotoRepository;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;


    /**
     * 이미지 개별 조회
     */
    @Transactional(readOnly = true)
    public PhotoDto findByFileId(Long id){

        Photo entity = photoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));

        PhotoDto photoDto = PhotoDto.builder()
                .origFileName(entity.getOrigFileName())
                .filePath(entity.getFilePath())
                .fileSize(entity.getFileSize())
                .build();

        return photoDto;
    }
}
