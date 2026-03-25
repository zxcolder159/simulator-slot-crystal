package simulator.crystal.slot.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public void register(@RequestBody AuthRequest request) {
		authService.register(request.login(), request.password());
	}

	@PostMapping("/login")
	public void login(@RequestBody AuthRequest request) {
		authService.login(request.login(), request.password());
	}
}
