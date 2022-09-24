package sejong.dormitory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sejong.dormitory.repository.PhotoRepository;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
}
