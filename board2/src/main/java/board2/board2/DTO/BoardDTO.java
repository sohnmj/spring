package board2.board2.DTO;

import board2.board2.entitiy.BoardEntity;
import board2.board2.entitiy.BoardFileEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDTO {
    private long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContent;
    private int boardHits;
    private LocalDateTime boardCreateTime;
    private  LocalDateTime boardUpdateTime;
    private List<MultipartFile> boardFile;
    private List<String> originalFileName;
    private List<String> storedFileName;
    private int fileAttached;
    public BoardDTO() {
    }

    static public BoardDTO TO_BoardDTO (BoardEntity entity){
        BoardDTO boardDTO=new BoardDTO();
        boardDTO.id=entity.getId();
        boardDTO.boardWriter= entity.getBoardWriter();
        boardDTO.boardContent= entity.getBoardContent();
        boardDTO.boardHits= entity.getBoardHits();
        boardDTO.boardPass=entity.getBoardPass();
        boardDTO.boardCreateTime=entity.getBoardCreateTime();
        boardDTO.boardUpdateTime=entity.getBoardUpdateTime();
        boardDTO.boardTitle= entity.getBoardTitle();
        if(entity.getFileAttached()==1){
            List<String>originalFileNames=new ArrayList<>();
            List<String>storedFileNames=new ArrayList<>();
            for(BoardFileEntity boardFileEntity: entity.getBoardFileEntityList()){
                originalFileNames.add(boardFileEntity.getOriginalFileName());
                storedFileNames.add(boardFileEntity.getStoredFileName());
            }
            boardDTO.setOriginalFileName(originalFileNames);
            boardDTO.setStoredFileName(storedFileNames);
            boardDTO.setFileAttached(1);
        }
        else{
            boardDTO.setFileAttached(0);
        }
        return boardDTO;
    }
    static public List<BoardDTO> TO_BoardDTOs (List<BoardEntity> entityList){
        List<BoardDTO>boardDTOS=new ArrayList<>();
        for(BoardEntity boardEntity:entityList){
            boardDTOS.add(BoardDTO.TO_BoardDTO(boardEntity));
        }
        return boardDTOS;
    }

    public BoardDTO(LocalDateTime boardCreateTime, int boardHits, String boardTitle, String boardWriter, long id) {
        this.boardCreateTime = boardCreateTime;
        this.boardHits = boardHits;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.id = id;
    }
}

