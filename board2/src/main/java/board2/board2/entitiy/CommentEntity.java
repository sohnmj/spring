package board2.board2.entitiy;

import board2.board2.DTO.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String commentWriter;
    @Column
    private String commentContents;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    static public CommentEntity toSaveCommentEntity(CommentDTO commentDTO,BoardEntity boardEntity){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        return commentEntity;
    }

}
