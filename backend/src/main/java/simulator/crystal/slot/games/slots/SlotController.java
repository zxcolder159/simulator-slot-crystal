package simulator.crystal.slot.games.slots;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import simulator.crystal.slot.user.UserService;
import simulator.crystal.slot.exceptions.GameNotFoundException;
import simulator.crystal.slot.exceptions.InvalidBetException;

import java.util.Map;


@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/slots")
@CrossOrigin(origins = "*")
public class SlotController {
	private final Map<String, Slot> allSlots;

	private final SlotService slotService;
	private final UserService userService;
	@PostMapping("/spin")
	public SlotResult spin(@RequestBody SpinRequest request) {
		Slot selectedSlot = allSlots.get(request.getSlotType());

		if (selectedSlot == null) {
			throw new GameNotFoundException("Game not found: " + request.getSlotType());
		}
		if(request.getBet() <= 0) {
			throw new InvalidBetException("Bet must be positive, provided: " + request.getBet());
		}
		SlotResult result = slotService.play(request.getUserId(), request.getBet(), selectedSlot);


		return new SlotResult(result.getSymbols(), result.getWin());
	}
}
