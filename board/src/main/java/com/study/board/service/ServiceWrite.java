package com.study.board.service;

import com.study.board.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;



import java.util.List;


public interface ServiceWrite {
    public void write(Board board, MultipartFile file)throws Exception;
    public Page<Board> BoardList(Pageable pageable);
    public Board boardView(Integer id);
    public void boardDelete(Integer id);
    public void boardUpdate(Integer id,Board board,MultipartFile file);
}
