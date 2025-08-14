package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.ServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String boardWritePro(Board entity,Model model){

        service.write(entity);
        model.addAttribute("message","글작성 완료");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }
    @GetMapping("/board/list")
    public String boardList(Model model){

        List<Board> boards = service.BoardList();
        model.addAttribute("list",boards);

        return "boardlist";
    }
    @GetMapping("/board/view")
    public String boardView(Model model,Integer id){
        model.addAttribute("board",service.boardView(id));
        return "boardview";
    }
    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        service.boardDelete(id);
        return "redirect:/board/list";
    }
    @GetMapping("/board/modify{id}")
    public String boardModify(Model model,@PathVariable("id") Integer id){
        model.addAttribute("board",service.boardView(id));
        return "boardmodify";
    }
    @PostMapping("/board/update{id}")
    public String boardModify(@PathVariable("id") Integer id, Board board){
        Board boardTemp=service.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());
        return "redirect:/board/list";
    }

}
