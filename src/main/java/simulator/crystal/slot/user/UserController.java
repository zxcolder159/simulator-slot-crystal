package simulator.crystal.slot.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/profile")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository userRepository;

	@GetMapping("/{userId}")
	public UserDTO getUserDTO(@PathVariable Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
		return new UserDTO(user.getUserName(), user.getAvatarUrl(), user.getBalance());
	}
}
