package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@Qualifier("MainDiscountPolicy")
public @interface MainDiscountPolicy {
}
