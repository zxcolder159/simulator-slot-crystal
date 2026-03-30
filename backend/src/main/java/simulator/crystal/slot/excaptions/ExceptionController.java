package simulator.crystal.slot.excaptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
		return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage(), 404, LocalDateTime.now()));
	}

	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<ErrorResponse> handleInsufficientFundsException(InsufficientFundsException ex) {
		return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), 400, LocalDateTime.now()));
	}

	@ExceptionHandler(GameNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleGameNotFoundException(GameNotFoundException ex) {
		return ResponseEntity.status(404).body(new ErrorResponse(ex.getMessage(), 404, LocalDateTime.now()));
	}

	@ExceptionHandler(InvalidBetException.class)
	public ResponseEntity<ErrorResponse> handleInvalidBetException(InvalidBetException ex) {
		return ResponseEntity.status(400).body(new ErrorResponse(ex.getMessage(), 400, LocalDateTime.now()));
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return ResponseEntity.status(409).body(new ErrorResponse(ex.getMessage(), 409, LocalDateTime.now()));
	}

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException ex) {
		return ResponseEntity.status(401).body(new ErrorResponse(ex.getMessage(), 401, LocalDateTime.now()));
	}

	@ExceptionHandler(JwtSecretKeyException.class)
	public ResponseEntity<ErrorResponse> handleJwtSecretKeyException(JwtSecretKeyException ex) {
		return ResponseEntity.status(500).body(new ErrorResponse(ex.getMessage(), 500, LocalDateTime.now()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		return ResponseEntity.status(500).body(new ErrorResponse(ex.getMessage(), 500, LocalDateTime.now()));
	}
}
