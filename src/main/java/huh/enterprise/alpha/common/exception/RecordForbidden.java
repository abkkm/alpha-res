package huh.enterprise.alpha.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class RecordForbidden extends RuntimeException{
    public RecordForbidden(String message) {
        super(message);
    }
}
