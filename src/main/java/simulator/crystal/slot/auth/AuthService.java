package simulator.crystal.slot.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.user.User;
import simulator.crystal.slot.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	public void register(String login, String password) {
		if(userRepository.existsByUserName(login)) {
			throw new RuntimeException("User already exists");
		}
		User user = User.builder()
				.userName(login)
				.password(passwordEncoder.encode(password))
				.balance(0L)
				.role("ROLE_USER")
				.build();
		user = userRepository.save(user);
	}

	public void login(String login, String password) {
		User user = userRepository.findByUserName(login)
				.orElseThrow(() -> new RuntimeException("User not found"));

		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
	}
}
