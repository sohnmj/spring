package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
    @Test
    void Join() {

        //given
        Member member=new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        Member member1 = memberService.findOne((saveId)).get();
        assertThat(member.getName()).isEqualTo(member1.getName());


    }
    @Test
    public void 중복(){
        //given
        Member member1=new Member();
        member1.setName("spring");
        Member member2 =new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
       IllegalStateException e=assertThrows(IllegalStateException.class,()->memberService.join(member2));
       assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
        /*try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){

        }*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}