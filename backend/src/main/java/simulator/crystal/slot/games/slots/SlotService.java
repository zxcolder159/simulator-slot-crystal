package simulator.crystal.slot.games.slots;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.data.BetHistory;
import simulator.crystal.slot.data.BetHistoryRepository;
import simulator.crystal.slot.user.UserService;
import simulator.crystal.slot.exceptions.InsufficientFundsException;
import simulator.crystal.slot.wallet.WalletService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SlotService {

	private final WalletService walletService;
	private final BetHistoryRepository betHistoryRepository;

	@Transactional
	SlotResult play(Long userId, Long bet, Slot slot) {
		SlotResult slotResult = slot.calculatePaylines(bet);
		Long netResult = walletService.processBet(userId, bet, slotResult.getWin());
		BetHistory history = BetHistory.builder()
				.userId(userId)
				.bet(bet)
				.win(slotResult.getWin())
				.gameName(slot.getName())
				.time(LocalDateTime.now())
				.build();
		betHistoryRepository.save(history);
		return slotResult;
	}

}
