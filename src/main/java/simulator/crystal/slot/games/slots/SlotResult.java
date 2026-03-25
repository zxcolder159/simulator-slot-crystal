package simulator.crystal.slot.games.slots;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Результат кручение слота.
 * Нужен для хранения данных, которые будут передавать в frontEnd.
 */
@Getter
@AllArgsConstructor
public class SlotResult {
	private final int[][] symbols;
	private final long win;
}
