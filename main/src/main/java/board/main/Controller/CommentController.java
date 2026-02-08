package board.main.Controller;

import board.main.DTO.CommentDTO;
import board.main.Entity.CommentEntity;
import board.main.Service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/save")
    public ResponseEntity<CommentDTO> saveComment(CommentDTO commentDTO){
        commentService.save(commentDTO);
        return ResponseEntity.ok().body(commentDTO);
    }
    @GetMapping("/member")
    public String memberComments(HttpSession httpSession, Model model){
        Long loginId = (Long)httpSession.getAttribute("loginId");
        List<CommentDTO> comments = commentService.findAllCommentByMember(loginId);
        model.addAttribute("comments", comments);
        return "MemberComment";
    }
}
