package huh.enterprise.alpha.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class ServiceNotImplemented extends RuntimeException {

    public ServiceNotImplemented(String message) {
        super(message);
    }

}
