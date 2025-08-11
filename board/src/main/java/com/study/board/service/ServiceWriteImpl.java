package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceWriteImpl implements ServiceWrite {

    private final BoardRepository boardRepository;
//    @Autowired
//    public ServiceImpl(BoardRepository boardRepository) {
//        this.boardRepository = boardRepository;
//    }
    @Transactional
    @Override
    public void write(Board board) {
        boardRepository.save(board);
    }

    @Override
    public List<Board> BoardList() {
        return boardRepository.findAll();
    }
}
