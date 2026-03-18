package simulator.crystal.slot.games.slots;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import simulator.crystal.slot.user.UserService;

@RequiredArgsConstructor
@Service
public class SlotService {

	private final UserService userService;

	@Transactional
	SlotResult play(Long userId, Long bet, Slot slot) {
		SlotResult slotResult;
		if(userService.trySpendMoney(bet, userId)) {
			slotResult = slot.calculatePaylines(bet);
			userService.addMoney(slotResult.getWin(), userId);
		}
		else {
			throw new RuntimeException("Нет денег!");
		}
		return slotResult;

	}

}
