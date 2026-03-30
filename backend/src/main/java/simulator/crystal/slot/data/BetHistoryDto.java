package simulator.crystal.slot.data;

import java.time.LocalDateTime;

public record BetHistoryDto(String gameName, long bet, long win, LocalDateTime time) {
}
