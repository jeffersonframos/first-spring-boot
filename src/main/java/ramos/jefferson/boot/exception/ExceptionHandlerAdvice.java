package ramos.jefferson.boot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    
    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity handleException(ResourceNotFounException exception){
        return throwException(exception);
    }
    
    private ResponseEntity throwException(BaseException baseException){
        return ResponseEntity.status(baseException.getHttpStatus()).body(baseException.getMessage());
    }

}
