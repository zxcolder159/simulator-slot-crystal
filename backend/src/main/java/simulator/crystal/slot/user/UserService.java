package simulator.crystal.slot.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.excaptions.UserNotFoundException;
import simulator.crystal.slot.excaptions.InsufficientFundsException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void addMoney(Long amount, Long userId) {
        User user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
    @Transactional
    public void trySpendMoney(Long amount, Long userId) {
        User user = userRepository.findByIdForUpdate(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (user.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds: required " + amount + ", available " + user.getBalance());
        }
        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);
    }

	public Long getBalance(Long userId) {
		return userRepository.findById(userId)
				.map(User::getBalance)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
	}
}
