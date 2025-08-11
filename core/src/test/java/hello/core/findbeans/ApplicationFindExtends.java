package hello.core.findbeans;

import hello.core.Disccount.DiscountPolicy;
import hello.core.Disccount.FixDiscountPolicy;
import hello.core.Disccount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationFindExtends {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);
    @Test
    void display(){
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class));
    }
    @Test
    void display2(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "values = "+beansOfType.get(key));
        }
    }
    @Test
    void display3(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        //Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "values = "+beansOfType.get(key));
        }
    }
    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
