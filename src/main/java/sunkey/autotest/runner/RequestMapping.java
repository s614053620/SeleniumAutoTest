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
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RequestMapping {

    String DEFAULT = "";
    int DEFAULT_PORT = 0;

    String protocol() default DEFAULT;

    String host() default DEFAULT;

    int port() default DEFAULT_PORT;

    String path() default DEFAULT;

    String query() default DEFAULT;

    String ref() default DEFAULT;

}
