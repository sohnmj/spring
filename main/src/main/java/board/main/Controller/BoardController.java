package board.main.Controller;

import board.main.DTO.BoardDTO;

import board.main.DTO.CommentDTO;
import board.main.Service.BoardService;
import board.main.Service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping ("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    @GetMapping("/my")
    public String MyBoardList(Model model, HttpSession session) {
        Long loginId = (Long)session.getAttribute("loginId");
        List<BoardDTO> all = boardService.findByIdAll(loginId);
        model.addAttribute("boardList", all);
        return "boardList";
    }
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

        List<CommentDTO> comments = commentService.findAllComment(id);
        BoardDTO board = boardService.findById(id);
        model.addAttribute("board", board);
        model.addAttribute("commentList", comments);

        return "boardView";
    }
    @GetMapping("/update/{id}")
    public String boardUpdateg(@PathVariable long id, Model model, HttpSession session) {
        Long boardMemberId = boardService.getBoardMemberId(id);
        Long loginId=(Long)session.getAttribute("loginId");
        if(boardMemberId.equals(loginId)){
            BoardDTO board = boardService.findById(id);
            model.addAttribute("boardUpdate", board);
            return "boardUpdate";
        }
        return "redirect:/board/"+id;

    }
    @PostMapping("/update/{id}")
    public String boardUpdatep(@PathVariable long id, BoardDTO boardDTO, HttpSession session, Model model) {
        Long boardMemberId = boardService.getBoardMemberId(id);
        Long loginId=(Long)session.getAttribute("loginId");
        System.out.println("loginId = " + loginId);
        System.out.println("boardMemberId = " + boardMemberId);
        if(boardMemberId.equals(loginId)){
            boardService.update(boardDTO,loginId);
            return "redirect:/board/";
        }

        return "redirect:/board/";
    }
}
