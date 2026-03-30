package simulator.crystal.slot.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
@CrossOrigin(origins = "*")
public class BetController {
	private final BetHistoryRepository betHistoryRepository;

	@GetMapping("/{userId}")
	public List<BetHistoryDto> getAllBetHistory(@PathVariable Long userId) {
		List<BetHistory> betHistoryList = betHistoryRepository.findAllByUserIdOrderByTimeDesc(userId);
		return betHistoryList.stream()
				.map(history -> new BetHistoryDto(
						history.getGameName(),
						history.getBet(),
						history.getWin(),
						history.getTime()))
				.toList();
	}
}
