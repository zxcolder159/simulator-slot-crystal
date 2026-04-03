package simulator.crystal.slot.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.user.User;
import simulator.crystal.slot.user.UserRepository;
import simulator.crystal.slot.wallet.Wallet;
import simulator.crystal.slot.exceptions.UserAlreadyExistsException;
import simulator.crystal.slot.exceptions.UserNotFoundException;
import simulator.crystal.slot.exceptions.InvalidPasswordException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public String register(String login, String password) {
		if(userRepository.existsByUserName(login)) {
			throw new UserAlreadyExistsException("User already exists: " + login);
		}
		
		Wallet wallet = Wallet.builder()
				.balance(0L)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();
		
		User user = User.builder()
				.userName(login)
				.password(passwordEncoder.encode(password))
				.wallet(wallet)
				.role("ROLE_USER")
				.build();
		user = userRepository.save(user);
		return jwtUtil.generateToken(login);
	}

	public String login(String login, String password) {
		User user = userRepository.findByUserName(login)
				.orElseThrow(() -> new UserNotFoundException("User not found: " + login));

		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new InvalidPasswordException("Invalid password for user: " + login);
		}
		return jwtUtil.generateToken(login);
	}
}
