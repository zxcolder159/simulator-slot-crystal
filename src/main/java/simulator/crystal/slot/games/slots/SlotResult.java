package simulator.crystal.slot.games.slots;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Результат кручение слота.
 * Нужен для хранения данных, которые будут передавать в frontEnd.
 */
@Getter
@RequiredArgsConstructor
public class SlotResult {
	private final int[] symbols;
	private final long win;

}
