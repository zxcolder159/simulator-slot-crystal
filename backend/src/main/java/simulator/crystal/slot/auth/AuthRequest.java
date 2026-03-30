package simulator.crystal.slot.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication request data transfer object")
public record AuthRequest(
	@Schema(description = "User login/username", example = "player123", required = true)
	String login, 
	@Schema(description = "User password", example = "securePassword123", required = true)
	String password) {
}
