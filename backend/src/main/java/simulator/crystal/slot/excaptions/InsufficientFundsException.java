package simulator.crystal.slot.excaptions;

public class InsufficientFundsException extends RuntimeException {
	public InsufficientFundsException(String message) {
		super(message);
	}
}
