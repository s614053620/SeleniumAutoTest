package sunkey.autotest.runner.event;

import lombok.*;

/**
 * @author Sunkey
 * @since 2021-05-28 2:34 下午
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UrlChangedEvent implements Event {

    private String currentUrl;

}
