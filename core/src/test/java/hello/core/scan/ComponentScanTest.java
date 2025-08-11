package hello.core.scan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentScanTest {

    @Test
    void diplay(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentScanAppConfig.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,()->ac.getBean(BeanB.class));
    }
    @Configuration
    @ComponentScan(includeFilters=@Filter(type= FilterType.ANNOTATION,classes =MyIncludeComponent.class),
        excludeFilters = @Filter(type=FilterType.ANNOTATION,classes=MyExcludeComponent.class)
    )
    static class ComponentScanAppConfig{

    }
}
