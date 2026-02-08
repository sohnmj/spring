package board.main.Controller;

import board.main.DTO.MemberDTO;
import board.main.Entity.MemberEntity;
import board.main.Service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/new")
    public String newMember(){
        return "memberSave";
    }

    @PostMapping("/save")
    public String memberSave(MemberDTO memberDTO){
        try {
            memberService.save(memberDTO);

        }
        catch (DataIntegrityViolationException e) {
            System.out.println("e = " + e);
        }
        return "redirect:/";
    }
    @PostMapping("/login")
    public String memberLogin(MemberDTO memberDTO, HttpSession session){
        System.out.println("memberDTO = " + memberDTO);
        int login = memberService.login(memberDTO);
        if(login==1){
            MemberEntity memberEntity = memberService.findByUserId(memberDTO);
            session.setAttribute("loginId",memberEntity.getId());
            return "redirect:/";
        }
        else{
            return "redirect:/";
        }
    }
    @GetMapping("/update")
    public String memberUpdateForm(HttpSession session, Model model){
        Long Id = (Long)session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.updateForm(Id);
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }

    @PostMapping("/update")
    public String memberUpdate(MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String memberDelete(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}
