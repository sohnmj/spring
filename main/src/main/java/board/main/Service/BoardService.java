package board.main.Service;
import board.main.DTO.BoardDTO;
import board.main.Entity.BoardEntity;
import board.main.Entity.MemberEntity;
import board.main.Repository.BoardRepository;
import board.main.Repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }
    public List<BoardDTO> findByIdAll(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).get();
        List<BoardEntity> boards = boardRepository.findByMemberEntity(memberEntity);
        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        for(BoardEntity board : boards){
            boardList.add(BoardDTO.toBoardDTO(board));
        }
        return boardList;
    }
    public void save(BoardDTO boardDTO, HttpSession session) {
        Long memberId = (Long)session.getAttribute("loginId");
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        boardRepository.save(BoardEntity.toBoardEntity(boardDTO,memberEntity));
    }
    public BoardDTO findById(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        return BoardDTO.toBoardDTO(boardEntity);
    }
    public Long getBoardMemberId(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        return boardEntity.getMemberEntity().getId();
    }
    public void update(BoardDTO boardDTO,Long memberId) {
        BoardEntity byId = boardRepository.findById(boardDTO.getId()).get();
        byId.setTitle(boardDTO.getTitle());
        byId.setContent(boardDTO.getContent());

        boardRepository.save(byId);
    }
}
