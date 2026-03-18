package simulator.crystal.slot.games.slots;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.data.BetHistory;
import simulator.crystal.slot.data.BetHistoryRepository;
import simulator.crystal.slot.user.UserService;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SlotService {

	private final UserService userService;
	private final BetHistoryRepository betHistoryRepository;

	@Transactional
	SlotResult play(Long userId, Long bet, Slot slot) {
		SlotResult slotResult;
		if(userService.trySpendMoney(bet, userId)) {
			slotResult = slot.calculatePaylines(bet);
			userService.addMoney(slotResult.getWin(), userId);
			BetHistory history = BetHistory.builder()
					.userId(userId)
					.bet(bet)
					.win(slotResult.getWin())
					.gameName(slot.getName())
					.time(LocalDateTime.now())
					.build();
			betHistoryRepository.save(history);
		}
		else {
			throw new RuntimeException("Нет денег!");
		}
		return slotResult;

	}

}
