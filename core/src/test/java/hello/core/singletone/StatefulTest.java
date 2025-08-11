package hello.core.singletone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulTest {
    @Test
    void display(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);
        Stateful stateful1=ac.getBean("stateful",Stateful.class);
        Stateful stateful2=ac.getBean("stateful",Stateful.class);
        stateful1.order("user1",1000);
        stateful2.order("user2",2000);
        int price=stateful1.getPrice();
        System.out.println("price = " + price);
    }
    static class TestConfig{
        @Bean
        public Stateful stateful(){
            return new Stateful();
        }
    }
}