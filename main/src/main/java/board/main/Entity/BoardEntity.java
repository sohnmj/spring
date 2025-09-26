package board.main.Entity;

import board.main.DTO.BoardDTO;
import jakarta.persistence.*;
import lombok.Data;

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
    static public BoardEntity toBoardEntity(BoardDTO boardDTO,MemberEntity memberEntity) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

}
