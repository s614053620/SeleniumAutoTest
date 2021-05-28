package sunkey.autotest.runner.route;

import lombok.Getter;
import lombok.Setter;
import sunkey.autotest.runner.AutoTest;
import sunkey.autotest.utils.Assert;
import sunkey.autotest.utils.Ordered;

/**
 * @author Sunkey
 * @since 2021-05-28 11:36 上午
 **/
public class AutoTestMatchRule extends UrlMatchRule implements Ordered {

    @Getter
    @Setter
    private int order;

    public AutoTestMatchRule(AutoTest clazz, AutoTest method) {
        if (clazz != null) {
            setValue(clazz);
        }
        setValue(method);
    }

    private void setValue(AutoTest ann) {
        Assert.notNull(ann, "ann");
        if (!ann.protocol().isEmpty()) {
            setProtocol(ann.protocol());
        }
        if (!ann.host().isEmpty()) {
            setHost(ann.host());
        }
        if (ann.port() != 0) {
            setPort(ann.port());
        }
        if (!ann.path().isEmpty()) {
            setPath(ann.path());
        }
        if (!ann.query().isEmpty()) {
            setQuery(ann.query());
        }
        if (!ann.ref().isEmpty()) {
            setRef(ann.ref());
        }
    }

}
