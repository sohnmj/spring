package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.Disccount.DiscountPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class Autowired {
    @Test
    void display(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountServices.class);

    }
    static class DiscountServices{
        private final Map<String, DiscountPolicy> policymap;
        private final List<DiscountPolicy> polices;

        public DiscountServices(List<DiscountPolicy> polices, Map<String, DiscountPolicy> policymap) {
            this.polices = polices;
            this.policymap = policymap;
            System.out.println("polices = " + polices);
            System.out.println("policymap = " + policymap);
        }
    }
}
