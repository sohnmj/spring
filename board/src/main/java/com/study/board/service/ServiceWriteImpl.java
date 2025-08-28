package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceWriteImpl implements ServiceWrite {

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public void write (Board board, MultipartFile file)throws Exception {
        if(!file.isEmpty()) {
            String projectPath= System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
            UUID uuid=UUID.randomUUID();
            String fileName=uuid+"_"+file.getOriginalFilename();
            File savefile=new File(projectPath,fileName);
            file.transferTo(savefile);
            board.setFilename(fileName);
            board.setFilepath("/files/"+fileName);
        }

        boardRepository.save(board);
    }
    @Transactional
    @Override
    public void boardUpdate(Integer id, Board board, MultipartFile file)throws Exception {
        if(!file.isEmpty()) {
            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
            Board boardTemp = boardView(id);
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + file.getOriginalFilename();
            File savefile = new File(projectPath, fileName);
            file.transferTo(savefile);
            boardTemp.setFilepath("/files/" + fileName);
            boardTemp.setFilename(fileName);
            boardTemp.setTitle(board.getTitle());
            boardTemp.setContent(board.getContent());
        }
        else{
            Board boardTemp = boardView(id);
            boardTemp.setTitle(board.getTitle());
            boardTemp.setContent(board.getContent());
        }
    }

    @Override
    public Page<Board> BoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public void boardDelete(Integer id) {
        Board board=boardView(id);
        fileDelete(board.getFilename());
        boardRepository.deleteById(id);
    }

    @Override
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    @Override
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword,pageable);
    }

    @Override
    public void fileDelete(String fileName) {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        File savefile = new File(projectPath, fileName);
        if(savefile.exists()){
            boolean delete = savefile.delete();

        }
    }
}
