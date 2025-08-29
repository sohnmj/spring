package board2.board2.entitiy;

import board2.board2.DTO.BoardDTO;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length=20,nullable = false)
    private String boardWriter;
    @Column
    private String boardPass;
    @Column
    private String boardTitle;
    @Column(length = 500)
    private String boardContent;
    @Column
    private int boardHits;
    @Column
    private int fileAttached;
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity>boardFileEntityList=new ArrayList<>();
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity>commentEntityList=new ArrayList<>();
    static public BoardEntity TO_BoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.boardWriter=boardDTO.getBoardWriter();
        boardEntity.boardPass=boardDTO.getBoardPass();
        boardEntity.boardTitle = boardDTO.getBoardTitle();
        boardEntity.boardContent=boardDTO.getBoardContent();
        boardEntity.boardHits=0;
        boardEntity.setFileAttached(0);
        return boardEntity;
    }
    static public BoardEntity TO_FileBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.boardWriter=boardDTO.getBoardWriter();
        boardEntity.boardPass=boardDTO.getBoardPass();
        boardEntity.boardTitle = boardDTO.getBoardTitle();
        boardEntity.boardContent=boardDTO.getBoardContent();
        boardEntity.boardHits=0;
        boardEntity.setFileAttached(1);
        return boardEntity;
    }
    static public BoardEntity TO_UpdateBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.id=boardDTO.getId();
        boardEntity.boardWriter=boardDTO.getBoardWriter();
        boardEntity.boardPass=boardDTO.getBoardPass();
        boardEntity.boardTitle = boardDTO.getBoardTitle();
        boardEntity.boardContent=boardDTO.getBoardContent();
        boardEntity.boardHits=boardDTO.getBoardHits();
        return boardEntity;
    }

}
