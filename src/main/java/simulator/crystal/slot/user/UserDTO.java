package simulator.crystal.slot.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User profile data transfer object")
public record UserDTO (
	@Schema(description = "Username of the user", example = "player123")
	String userName, 
	
	@Schema(description = "URL of the user's avatar image", example = "https://example.com/avatar.png")
	String avatarURL, 
	
	@Schema(description = "Current balance of the user", example = "1000")
	Long balance) {

}
