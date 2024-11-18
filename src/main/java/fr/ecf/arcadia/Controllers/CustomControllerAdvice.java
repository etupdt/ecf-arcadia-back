package fr.ecf.arcadia.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;

// @ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler(NullPointerException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleNullPointerExceptions(
        Exception e
    ) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.NOT_FOUND; // 404

        return new ResponseEntity<>(
            new ErrorResponse(
              status, 
              e.getMessage()
            ),
            status
        );
    }

    @ExceptionHandler(AccessDeniedException.class) // exception handled
    public ResponseEntity<ErrorResponse> handleAccessDeniedExceptions(
        Exception e
    ) {
        // ... potential custom logic

        HttpStatus status = HttpStatus.FORBIDDEN; // 403

        return new ResponseEntity<>(
            new ErrorResponse(
              status, 
              e.getMessage()
            ),
            status
        );
    }

    // // fallback method
    // @ExceptionHandler(Exception.class) // exception handled
    // public ResponseEntity<ErrorResponse> handleExceptions(
    //     Exception e
    // ) {
    //     // ... potential custom logic

    //     HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

    // // converting the stack trace to String
    // StringWriter stringWriter = new StringWriter();
    // PrintWriter printWriter = new PrintWriter(stringWriter);
    // e.printStackTrace(printWriter);
    // String stackTrace = stringWriter.toString();

    //     return new ResponseEntity<>(
    //         new ErrorResponse(
    //           status, 
    //           e.getMessage(), 
    //           stackTrace 
    //         ),
    //         status
    //     );
    // }
}