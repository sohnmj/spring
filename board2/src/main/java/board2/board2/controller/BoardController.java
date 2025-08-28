package board2.board2.controller;

import board2.board2.DTO.BoardDTO;
import board2.board2.entitiy.BoardEntity;
import board2.board2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/save")
    public String home(){
        return "write";
    }
    @PostMapping("/save")
    public String save(BoardDTO boardDTO) throws IOException {
        boardService.save(boardDTO);
        return "redirect:/";
    }
    @GetMapping("/")
    public String BoardList(Model model){
        List<BoardDTO> boardList = boardService.findALL();
        model.addAttribute("boardList",boardList);
        System.out.println(1);
        return "list";
    }
    @GetMapping("/{id}")
    public String boardView(@PathVariable Long id,Model model,@PageableDefault(page=1)Pageable pageable){
        boardService.updateHits(id);
        BoardDTO board = boardService.findById(id);
        model.addAttribute("board",board);
        model.addAttribute("page",pageable.getPageNumber());
        return "view";
    }
    @GetMapping("/update/{id}")
    public String boardUpdate(@PathVariable Long id,Model model){
        BoardDTO board = boardService.findById(id);
        model.addAttribute("boardUpdate",board);
        return "update";

    }
    @PostMapping("/update")
    public String boardUpdatePost(BoardDTO boardDTO, Model model){
        BoardDTO board=boardService.update(boardDTO);
        model.addAttribute("board",board);
        return "view";
    }
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }
    @GetMapping("/paging")
    public String boardPageList(@PageableDefault(page=1)Pageable pageable,Model model){
        Page<BoardDTO>pageList= boardService.paging(pageable);
        int nowPage=pageable.getPageNumber();
        int startPage=Math.max(nowPage-2,1);
        int endPage=Math.min(nowPage+2, pageList.getTotalPages());
        model.addAttribute("boardList",pageList);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "paging";
    }

}
