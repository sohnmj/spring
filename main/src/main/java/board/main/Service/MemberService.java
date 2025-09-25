package board.main.Service;

import board.main.DTO.MemberDTO;
import board.main.Entity.MemberEntity;
import board.main.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO){
        MemberEntity memberEntity = MemberEntity.To_MemberEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
    public int login(MemberDTO memberDTO){
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());
        if(byUserId.isPresent()){
            MemberEntity memberEntity = byUserId.get();
            if(memberEntity.getPassword().equals(memberDTO.getPassword())){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            System.out.println("NO");
            return 0;
        }
    }
    public List<MemberDTO> findAll(){
        List<MemberEntity> memberList = memberRepository.findAll();
        List<MemberDTO> memberDTOList=new ArrayList<>();
        for(MemberEntity entity:memberList){
            memberDTOList.add(MemberDTO.To_MemberDTO(entity));
        }
        return memberDTOList;
    }
    public MemberDTO updateForm(Long userId){
        Optional<MemberEntity> byUserId = memberRepository.findById(userId);
        if(byUserId.isPresent()){
            MemberEntity memberEntity=byUserId.get();
            MemberDTO memberDTO = MemberDTO.To_MemberDTO(memberEntity);
            memberDTO.setId(memberEntity.getId());

            return memberDTO;
        }
        else{
            return null;
        }

    }
    public void update(MemberDTO memberDTO){
        MemberEntity memberEntity=MemberEntity.To_MemberEntity(memberDTO);
        System.out.println("memberDTO = " + memberDTO);
        memberEntity.setId(memberDTO.getId());
        memberRepository.save(memberEntity);
    }
    public void deleteById(long id){
        memberRepository.deleteById(id);
    }



}
