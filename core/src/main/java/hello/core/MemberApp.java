package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberserviceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//순수 자바언어로 테스트(spring X)
public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig=new AppConfig();
//        MemberService memberService=appConfig.memberService();
                //new MemberserviceImpl();
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member( "member1",1L, Grade.VIP);
        memberService.join(member);
        Member findmember = memberService.findMember(1L);
        System.out.println(member);
        System.out.println("findmember = " + findmember);
    }
}
