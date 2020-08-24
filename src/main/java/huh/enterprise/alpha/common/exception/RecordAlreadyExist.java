package huh.enterprise.alpha.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class RecordAlreadyExist extends RuntimeException {
    public RecordAlreadyExist(String message) {
        super(message);
    }
}
