package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.ServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class BoardController {
    ServiceWrite service;
    @Autowired
    public BoardController(ServiceWrite service) {
        this.service = service;
    }

    @GetMapping("/board/write")
    public String boardWrite(){
        return "boardwrite";
    }
    @PostMapping("/board/writepro")
    public String boardWritePro(Board entity){
        service.write(entity);
        return "redirect:/board/write";
    }
    @GetMapping("/board/list")
    public String boardList(Model model){

        List<Board> boards = service.BoardList();
        model.addAttribute("list",boards);

        return "boardlist";
    }


}
