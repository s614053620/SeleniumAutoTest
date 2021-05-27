package sunkey.autotest.runner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Sunkey
 * @since 2021-05-27 6:01 下午
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AutoTest {

    String MATCH_ALL = "*";

    /**
     * 页面URI
     * * : matchAll
     *
     * @return
     */
    String value() default MATCH_ALL;

    String host() default MATCH_ALL;

}
