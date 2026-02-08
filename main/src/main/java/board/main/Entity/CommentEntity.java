package board.main.Entity;

import board.main.DTO.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;
    static public CommentEntity To_CommentEntity(CommentDTO commentDTO,MemberEntity memberEntity,BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(commentDTO.getComment());
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
