package board2.board2.DTO;

import board2.board2.entitiy.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;
    static public CommentDTO toSaveCommentDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentCreatedTime(commentEntity.getBoardCreateTime());
        commentDTO.setId(commentEntity.getId());
        return commentDTO;
    }

}
