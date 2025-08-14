package com.study.board.service;

import com.study.board.entity.Board;


import java.util.List;


public interface ServiceWrite {
    public void write(Board board);
    public List<Board> BoardList();
    public Board boardView(Integer id);
    public void boardDelete(Integer id);
}
