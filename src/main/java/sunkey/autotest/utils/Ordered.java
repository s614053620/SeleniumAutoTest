package sunkey.autotest.utils;

/**
 * @author Sunkey
 * @since 2021-05-28 7:11 下午
 **/
public interface Ordered {

    int MAX_ORDER = Integer.MIN_VALUE;
    int MIN_ORDER = Integer.MAX_VALUE;
    int DEFAULT_ORDER = 0;

    int getOrder();

}
