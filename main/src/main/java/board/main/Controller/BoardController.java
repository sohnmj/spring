package board.main.Controller;

import board.main.DTO.BoardDTO;
import board.main.Entity.BoardEntity;
import board.main.Service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping ("/board")
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/")
    public String BoardList(Model model) {
        List<BoardDTO> all = boardService.findAll();
        model.addAttribute("boardList", all);
        return "boardList";
    }
    @GetMapping("/new")
    public String boardWrite(){
        return "write";
    }
    @PostMapping ("/new")
    public String newBoard(BoardDTO boardDTO, HttpSession session) {
        boardService.save(boardDTO,session);
        return "redirect:/board/";
    }
    @GetMapping("/{id}")
    public String boardView(@PathVariable long id, Model model) {
        BoardDTO board = boardService.findById(id);
        model.addAttribute("board", board);
        return "boardView";
    }
}
