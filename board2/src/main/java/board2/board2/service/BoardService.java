package board2.board2.service;

import board2.board2.DTO.BoardDTO;
import board2.board2.entitiy.BaseEntity;
import board2.board2.entitiy.BoardEntity;
import board2.board2.entitiy.BoardFileEntity;
import board2.board2.repositroty.BoardFIleRepository;
import board2.board2.repositroty.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFIleRepository boardFIleRepository;
    public void save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().isEmpty()) {
            BoardEntity boardEntity=BoardEntity.TO_BoardEntity(boardDTO);
            boardRepository.save(boardEntity);
        }
        else{
            BoardEntity boardEntity = BoardEntity.TO_FileBoardEntity(boardDTO);
            long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(savedId).get();
            for(MultipartFile boardFile: boardDTO.getBoardFile()) {
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + '_' + originalFileName;
                String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\"+storedFileName;
                boardFile.transferTo(new File(savePath));
                boardFIleRepository.save(BoardFileEntity.toBoardFileEntity( board, originalFileName,storedFileName));
            }
        }

    }
    @Transactional
    public List<BoardDTO> findALL(){
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDTO>boardList=BoardDTO.TO_BoardDTOs(entityList);
        return boardList;
    }

    @Transactional
    public void updateHits(long id){
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(long id){
        BoardEntity boardEntity = boardRepository.findById(id).get();
        return BoardDTO.TO_BoardDTO(boardEntity);
    }



    @Transactional
    public BoardDTO update(BoardDTO boardDTO){
        BoardEntity boardEntity = BoardEntity.TO_UpdateBoardEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }
    public void delete(Long id){
        boardRepository.deleteById(id);
    }
    public Page<BoardDTO> paging(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber() - 1;
        int pageLimit=3;
        Page<BoardEntity> boardEntity = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
        System.out.println(boardEntity.getContent());
        Page<BoardDTO> boardDTOs=boardEntity.map(board->new BoardDTO(board.getBoardCreateTime(),board.getBoardHits(),board.getBoardTitle(),board.getBoardWriter(),board.getId()));
        return boardDTOs;
    }

}
