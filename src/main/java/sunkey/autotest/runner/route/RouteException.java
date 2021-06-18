package sunkey.autotest.runner.route;

/**
 * @author Sunkey
 * @since 2021-05-28 11:49 上午
 **/
public class RouteException extends RuntimeException {

    public RouteException() {
    }

    public RouteException(String message) {
        super(message);
    }

    public RouteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteException(Throwable cause) {
        super(cause);
    }
}
