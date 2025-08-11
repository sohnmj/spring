package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberserviceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Configuration {
    @Test
    void display(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        MemberserviceImpl memberService = ac.getBean(MemberserviceImpl.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        Assertions.assertThat(memberRepository1).isEqualTo(memberRepository);
        Assertions.assertThat(memberRepository2).isEqualTo(memberRepository);

    }
    @Test
    void display2(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig appConfig = ac.getBean(AppConfig.class);
        System.out.println("appConfig = " + appConfig.getClass());
    }
}
