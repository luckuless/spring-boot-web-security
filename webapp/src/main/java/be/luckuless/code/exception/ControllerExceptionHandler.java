package be.luckuless.code.exception;

import be.luckuless.code.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(CarNotFoundException ex, WebRequest request) {
        ErrorMessage error = new ErrorMessage();
        error.setCode(100);
        error.setMessage(ex.getMessage());
        return error;
    }
}
