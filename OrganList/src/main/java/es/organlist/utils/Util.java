package es.organlist.utils;

import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

/**
 * The type Util.
 */
@Component
public class Util {

    /**
     * Check empty list.
     *
     * @param empty   the empty
     * @param message the message
     */
    public static void checkEmptyList(boolean empty, String message) {
        if (empty) {
            throw new NotFoundException(message);
        }
    }
}
