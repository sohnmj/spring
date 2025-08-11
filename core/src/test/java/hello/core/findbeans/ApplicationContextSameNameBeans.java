package hello.core.findbeans;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameNameBeans {
    ApplicationContext ac=new AnnotationConfigApplicationContext(SameBeanConfig.class);
    @Test
    void find(){
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(MemberRepository.class));

    }
    @Test
    void find1(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key+"value"+beansOfType.get(key));

        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }@Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
