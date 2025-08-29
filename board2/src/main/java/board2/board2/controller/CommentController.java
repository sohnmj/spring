package board2.board2.controller;

import board2.board2.DTO.CommentDTO;
import board2.board2.service.BoardService;
import board2.board2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    @PostMapping("/save")
    public ResponseEntity commentSave(@ModelAttribute CommentDTO commentDTO, Model model){
        Long save = commentService.save(commentDTO);
        //만약 성공
        if(save!=null){
            System.out.println("save = " + save);
            List<CommentDTO> commentList = commentService.findAll(commentDTO.getBoardId());
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("x",HttpStatus.NOT_FOUND);
        }

    }
}
