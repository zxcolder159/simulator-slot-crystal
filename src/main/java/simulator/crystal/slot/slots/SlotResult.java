package simulator.crystal.slot.slots;

import lombok.RequiredArgsConstructor;

/**
 * Результат кручение слота.
 * Нужен для хранения данных, которые будут передавать в frontEnd.
 */
@RequiredArgsConstructor
public class SlotResult {
	private final int[] symbols;
	private final long result;

}
