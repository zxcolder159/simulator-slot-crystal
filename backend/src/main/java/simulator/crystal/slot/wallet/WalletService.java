package simulator.crystal.slot.wallet;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.exceptions.InsufficientFundsException;
import simulator.crystal.slot.exceptions.UserNotFoundException;
import simulator.crystal.slot.user.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WalletService {
	private final WalletRepository walletRepository;

	@Transactional
	public void addMoney(Long amount, Long userId) {
		Wallet wallet = getWalletForUpdate(userId);
		updateBalance(wallet, amount);
	}

	@Transactional
	public void trySpendMoney(Long amount, Long userId) {
		Wallet wallet = getWalletForUpdate(userId);
		checkSufficientFunds(wallet, amount);
		updateBalance(wallet, -amount);
	}

	public Long getBalance(Long userId) {
		return walletRepository.findByUserId(userId)
				.map(Wallet::getBalance)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	@Transactional
	public Long processBet(Long userId, Long bet, Long win) {
		Wallet wallet = getWalletForUpdate(userId);
		checkSufficientFunds(wallet, bet);
		updateBalance(wallet, -bet + win);
		return win - bet;
	}

	private Wallet getWalletForUpdate(Long userId) {
		return walletRepository.findByUserIdForUpdate(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	private void checkSufficientFunds(Wallet wallet, Long requiredAmount) {
		if (wallet.getBalance() < requiredAmount) {
			throw new InsufficientFundsException("Insufficient funds: required " + requiredAmount + ", available " + wallet.getBalance());
		}
	}

	private void updateBalance(Wallet wallet, Long amountChange) {
		wallet.setBalance(wallet.getBalance() + amountChange);
		wallet.setUpdatedAt(LocalDateTime.now());
		walletRepository.save(wallet);
	}

}
