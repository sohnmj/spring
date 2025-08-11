package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class swithpTest1 {
    @Test
    void PrototypeTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
    }
    @Test
    void SingletonTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean bean1 = ac.getBean(ClientBean.class);
        int logic1 = bean1.logic();
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int logic2 = bean2.logic();
        assertThat(logic2).isEqualTo(1);

    }

    static class ClientBean{

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
        public int logic(){
            PrototypeBean bean = prototypeBeanProvider.get();
            bean.addCount();
            return bean.getCount();
        }
    }
    @Scope("prototype")
    static class PrototypeBean {
        private int count=0;
        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init : "+this);
        }
        @PreDestroy
        void destroy(){
            System.out.println("prototypeBean.destroy");
        }

    }
}
