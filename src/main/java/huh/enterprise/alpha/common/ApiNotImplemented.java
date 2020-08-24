package huh.enterprise.alpha.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class ApiNotImplemented extends RuntimeException {

    public ApiNotImplemented(String message) {
        super(message);
    }
}
