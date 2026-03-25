package simulator.crystal.slot.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void addMoney(Long amount, Long userId) {
        User user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
    @Transactional
    public boolean trySpendMoney(Long amount, Long userId) {
        User user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getBalance() < amount) {
            return false;
        }
        user.setBalance(user.getBalance() - amount);
		userRepository.save(user);
        return true;
    }

	public Long getBalance(Long userId) {
		return userRepository.findById(userId)
				.map(User::getBalance)
				.orElseThrow(() -> new RuntimeException("Пользователь не найден"));
	}
}
