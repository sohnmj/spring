package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.findbeans.ApplicationContext;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTestTest {
    @Test
    void display(){
        SingleToneTest singleToneTest1= SingleToneTest.getInstance();
        SingleToneTest singleToneTest2= SingleToneTest.getInstance();
        System.out.println("singleToneTest1 = " + singleToneTest1);
        System.out.println("singleToneTest2 = " + singleToneTest2);
        Assertions.assertThat(singleToneTest1).isSameAs(singleToneTest2);
    }
    @Test
    void display1(){
//        SingleToneTest singleToneTest1= SingleToneTest.getInstance();
//        SingleToneTest singleToneTest2= SingleToneTest.getInstance();
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

        Assertions.assertThat(ac.getBean(MemberService.class)).isSameAs(ac.getBean(MemberService.class));
    }
}
