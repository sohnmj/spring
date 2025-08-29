package board2.board2.service;

import board2.board2.DTO.CommentDTO;
import board2.board2.entitiy.BoardEntity;
import board2.board2.entitiy.CommentEntity;
import board2.board2.repositroty.BoardRepository;
import board2.board2.repositroty.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    public Long save(CommentDTO commentDTO){
        //해당 게시글 id의 게시물 받기
        Optional<BoardEntity> byId = boardRepository.findById(commentDTO.getBoardId());
        //게시물이 존재한다면
        if(byId.isPresent()){
            BoardEntity boardEntity = byId.get();
            return commentRepository.save(CommentEntity.toSaveCommentEntity(commentDTO,boardEntity)).getId();
        }
        //존재안하면
        else{
            return null;
        }
    }
    public List<CommentDTO> findAll(Long boardId){
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity>commentEntityList=commentRepository.findByBoardEntityOrderByBoardCreateTimeDesc(boardEntity);
        List<CommentDTO>commentDTOList=new ArrayList<>();
        for(CommentEntity commentEntity:boardEntity.getCommentEntityList()){
            commentDTOList.add(CommentDTO.toSaveCommentDTO(commentEntity));
        }

        return commentDTOList;
    }
}
