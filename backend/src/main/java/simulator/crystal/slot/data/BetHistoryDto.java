package simulator.crystal.slot.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Bet history data transfer object")
public record BetHistoryDto(
	@Schema(description = "Name of the game", example = "Classic Slot")
	String gameName, 
	
	@Schema(description = "Bet amount", example = "100")
	long bet, 
	
	@Schema(description = "Win amount", example = "250")
	long win, 
	
	@Schema(description = "Time of the bet", example = "2024-01-15T10:30:00")
	LocalDateTime time) {
}
