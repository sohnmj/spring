package board.main.DTO;

import board.main.Entity.MemberEntity;
import lombok.Data;

@Data
public class MemberDTO {
    private Long id;
    private String email;
    private String userId;
    private String password;
    static public MemberDTO To_MemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setId(memberEntity.getId());
        return memberDTO;
    }


}
