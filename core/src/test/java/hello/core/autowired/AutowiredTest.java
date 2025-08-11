package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.bean.override.convention.TestBean;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void display() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{
        @Autowired(required = false)
        void setNoBean1(Member member1){
            System.out.println("member1 = " + member1);
        }
        @Autowired()
        void setNoBean2(@Nullable Member member2){
            System.out.println("member2 = " + member2);
        }
        @Autowired()
        void setNoBean3(Optional<Member> member3){
            System.out.println("member3 = " + member3);
        }
    }
}
