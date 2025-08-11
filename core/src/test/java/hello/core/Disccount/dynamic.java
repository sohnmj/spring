package hello.core.Disccount;

import hello.core.AutoAppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class dynamic {

    @Test
    void display() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member=new Member("member1",1L, Grade.VIP);
        int rateDiscountPolicy = discountService.discount("rateDiscountPolicy", member, 10000);
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
        Assertions.assertThat(discountService.discount("rateDiscountPolicy", member, 10000)).isEqualTo(1000);

    }
    static class DiscountService{
        private final Map<String,DiscountPolicy> discountPolicyMap;
        private final List<DiscountPolicy>discountPolicies;

        public DiscountService(List<DiscountPolicy> discountPolicies, Map<String, DiscountPolicy> discountPolicyMap) {
            this.discountPolicies = discountPolicies;
            this.discountPolicyMap = discountPolicyMap;
        }
        int discount(String discountcode,Member member,int price ){
            DiscountPolicy discountPolicy = discountPolicyMap.get(discountcode);
            return discountPolicy.discount(member,price);
        }


    }

}
