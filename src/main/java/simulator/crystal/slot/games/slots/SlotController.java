package simulator.crystal.slot.games.slots;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Getter
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/slots")
public class SlotController {
	private final Map<String, Slot> allSlots;

	private final SlotService slotService;

	@PostMapping("/spin")
	public SlotResult spin(@RequestBody SpinRequest request) {
		Slot selectedSlot = allSlots.get(request.getSlotType());

		if (selectedSlot == null) {
			throw new RuntimeException("Такой игры не существует!");
		}

		return slotService.play(request.getUserId(), request.getBet(), selectedSlot);
	}
}
