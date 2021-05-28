package sunkey.autotest.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sunkey
 * @since 2021-05-28 7:06 下午
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Order {

    int MAX = Ordered.MAX_ORDER;
    int MIN = Ordered.MIN_ORDER;

    int value() default Ordered.DEFAULT_ORDER;

}
