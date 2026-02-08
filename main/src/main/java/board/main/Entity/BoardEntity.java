package board.main.Entity;

import board.main.DTO.BoardDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList=new ArrayList<>();

    static public BoardEntity toBoardEntity(BoardDTO boardDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }
    static public BoardEntity toUpdateBoardEntity(BoardDTO boardDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }
}
