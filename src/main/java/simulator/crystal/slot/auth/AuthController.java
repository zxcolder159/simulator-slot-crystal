package simulator.crystal.slot.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "API for user authentication")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@Operation(summary = "Register a new user", description = "Creates a new user account with the provided credentials")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User successfully registered"),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "409", description = "User already exists")
	})
	@PostMapping("/register")
	public void register(@RequestBody AuthRequest request) {
		authService.register(request.login(), request.password());
	}

	@Operation(summary = "Authenticate user", description = "Authenticates a user and returns a token")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "User successfully authenticated"),
		@ApiResponse(responseCode = "401", description = "Invalid credentials")
	})
	@PostMapping("/login")
	public void login(@RequestBody AuthRequest request) {
		authService.login(request.login(), request.password());
	}
}
