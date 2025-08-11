package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test

    void Join() {

        //given
        Member member=new Member();
        member.setName("spring09090");
        //when
        Long saveId = memberService.join(member);
        Member member1 = memberService.findOne((saveId)).get();
        assertThat(member.getName()).isEqualTo(member1.getName());


    }
    @Test
    public void 중복(){
        //given
        Member member1=new Member();
        member1.setName("spring1");
        Member member2 =new Member();
        member2.setName("spring1");
        //when
        memberService.join(member1);
       IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));
       assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");


    }

}