package board.main.Service;

import board.main.DTO.CommentDTO;
import board.main.Entity.BoardEntity;
import board.main.Entity.CommentEntity;
import board.main.Entity.MemberEntity;
import board.main.Repository.BoardRepository;
import board.main.Repository.CommentRepository;
import board.main.Repository.MemberRepository;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public boolean save(CommentDTO commentDTO){
        BoardEntity byId = boardRepository.findById(commentDTO.getBoardId()).get();
        MemberEntity memberEntity = memberRepository.findById(commentDTO.getMemberId()).get();
        CommentEntity commentEntity = CommentEntity.To_CommentEntity(commentDTO, memberEntity, byId);
        commentRepository.save(commentEntity);
        return true;
    }
    public List<CommentDTO> findAllComment(Long boardId){
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> comments = commentRepository.findByBoardEntityOrderByIdDesc(boardEntity);

        return To_CommentDTO(comments);
    }
    public List<CommentDTO> findAllCommentByMember(Long MemberId){
        MemberEntity memberEntity = memberRepository.findById(MemberId).get();
        List<CommentEntity> comments = commentRepository.findByMemberEntityOrderByIdDesc(memberEntity);
        return To_CommentDTO(comments);
    }
    public List<CommentDTO> To_CommentDTO(List<CommentEntity> comments){

        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(CommentEntity commentEntity : comments){
            commentDTOS.add(CommentDTO.TO_CommentDTO(commentEntity));
        }
        return commentDTOS;
    }
}
