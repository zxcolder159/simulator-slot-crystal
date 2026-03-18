package simulator.crystal.slot.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Getter
@RequestMapping("/api/v1/history")
public class BetController {
	private final BetHistoryRepository betHistoryRepository;

	@GetMapping("/{usedId}")
	public List<BetHistory> getAllBetHistory(@PathVariable Long userId) {
		return betHistoryRepository.findAllByUserIdOrderByTimeDesc(userId);
	}
}
