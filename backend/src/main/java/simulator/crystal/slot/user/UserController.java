package simulator.crystal.slot.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import simulator.crystal.slot.excaptions.UserNotFoundException;

@Tag(name = "User Profile", description = "API for managing user profiles")
@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository userRepository;

	@Operation(summary = "Get user profile by ID", description = "Retrieves user profile information including username, avatar, and balance")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved user profile"),
		@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/{userId}")
	public UserDTO getUserDTO(
		@Parameter(description = "ID of the user to retrieve", required = true)
		@PathVariable Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		return new UserDTO(user.getUserName(), user.getAvatarUrl(), user.getBalance());
	}
}
