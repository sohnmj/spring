package board.main.DTO;

import board.main.Entity.BoardEntity;
import lombok.Data;

@Data
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private Long memberId;
    static public BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        return boardDTO;
    }
}
