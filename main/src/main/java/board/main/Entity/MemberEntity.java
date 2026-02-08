package board.main.Entity;

import board.main.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity

public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String userId;
    @Column
    private String password;
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardEntityList=new ArrayList<>();
    static public MemberEntity To_MemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity=new MemberEntity();
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setPassword(memberDTO.getPassword());
        return memberEntity;
    }
}
