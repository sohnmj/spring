package board.main.DTO;

import board.main.Entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentDTO {
    private Long id;
    private String comment;
    private Long memberId;
    private Long boardId;
    static public CommentDTO TO_CommentDTO(CommentEntity commentEntity)
    {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setComment(commentEntity.getComment());
        commentDTO.setMemberId(commentEntity.getMemberEntity().getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        return commentDTO;
    }
}
