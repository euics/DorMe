package sejong.dormitory.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sejong.dormitory.dto.BoardDto;
import sejong.dormitory.entity.Board;
import sejong.dormitory.entity.Member;
import sejong.dormitory.entity.Photo;
import sejong.dormitory.repository.BoardRepository;
import sejong.dormitory.repository.MemberRepository;
import sejong.dormitory.repository.PhotoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PhotoRepository photoRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;

    public Page<Board> paging(int page) {
        return boardRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Transactional
    public void createBoard(BoardDto boardDto,
                            List<MultipartFile> files) throws Exception {
        // 파일 처리를 위한 Board 객체 생성
        Board board = new Board(
                boardDto.getMember(),
                boardDto.getTitle(),
                boardDto.getContent(),
                boardDto.getDateTime(),
                boardDto.getCreatedBy(),
                boardDto.getCountVisit()
        );

        List<Photo> photoList = fileHandler.parseFileInfo(files);
        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                // 파일을 DB에 저장
                System.out.println("save compl");
                board.addPhoto(photoRepository.save(photo));
            }
        }
        boardRepository.save(board);
    }

    @Transactional
    public void updatePost(Long id, BoardDto boardDto,
                           List<MultipartFile> files) throws Exception{
        Board board = boardRepository.findById(id).orElseThrow((() ->
                new IllegalStateException("해당 게시글이 존재하지 않습니다.")));

        List<Photo> photoList = fileHandler.parseFileInfo(files);
        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                board.addPhoto(photoRepository.save(photo));
            }
        }

        String created_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        board.update(boardDto.getTitle(),boardDto.getContent(),created_date,photoList);
    }

    @Transactional
    public void deletePost(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow((() ->
                new IllegalStateException("해당 게시글이 존재하지 않습니다.")));

        boardRepository.delete(board);
    }

    @Transactional
    public void updateCountVisit(Board board, Long countVisit){
        // setter 안쓰도록 수정할 것.
        board.setCountVisit(countVisit);
    }

    public Page<Board> searchByTitleOrContent(String searchText1, String searchText2, Pageable pageable){
        return boardRepository.searchByTitleOrContent(searchText1, searchText2, pageable);
    }

    public Board findById(Long id){
        return boardRepository.findById(id).get();
    }

}
