package simulator.crystal.slot.user;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Сохраняет нового пользователя в БД.
     * @param user
     * @return всегда возвращает пользователя.
     */
    @Transactional
    public User createUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return userRepository.getReferenceById(user.getId());
        }
        return userRepository.save(user);
    }
    @Transactional
    public void addMoney(Long amount, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
    @Transactional
    public boolean trySpendMoney(Long amount, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getBalance() < amount) {
            return false;
        }
        user.setBalance(user.getBalance() - amount);
        return true;
    }
}
