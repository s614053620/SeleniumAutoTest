package sunkey.autotest.utils;

import java.lang.reflect.AnnotatedElement;
import java.util.Comparator;

/**
 * @author Sunkey
 * @since 2021-05-28 7:10 下午
 **/
public class OrderComparator implements Comparator<Object> {

    public static final OrderComparator INSTANCE = new OrderComparator();

    private OrderComparator() {
    }

    public static boolean supportOrder(Object target) {
        if (target != null) {
            if (target instanceof Ordered) {
                return true;
            }
            if (target instanceof AnnotatedElement) {
                return ((AnnotatedElement) target).isAnnotationPresent(Order.class);
            }
        }
        return false;
    }

    public static int getOrder(Object target) {
        if (target != null) {
            if (target instanceof Ordered) {
                return ((Ordered) target).getOrder();
            }
            if (target instanceof AnnotatedElement) {
                Order ann = ((AnnotatedElement) target).getAnnotation(Order.class);
                if (ann != null) {
                    return ann.value();
                }
            }
        }
        return Ordered.DEFAULT_ORDER;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return getOrder(o1) - getOrder(o2);
    }

}
