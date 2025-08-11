package com.study.board.service;

import com.study.board.entity.Board;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ServiceWrite {
    public void write(Board board);
    public List<Board> BoardList();

}
