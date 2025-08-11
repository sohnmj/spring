package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void LifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanLifeConfig.class);
        ac.getBean("networkClient",NetworkClient.class);
        ac.close();
    }
    @Configuration
    static class BeanLifeConfig{
        //destroymethod의 기본값은 추론값이기 떄문에 따로 지정안한다면 알아서 Bean객체에 있는 method중 close나 shutdown 이라는 method가 자동으로 등록됨
        //@Bean(initMethod = "init",destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }
}
