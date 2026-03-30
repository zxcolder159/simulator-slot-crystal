package simulator.crystal.slot.games.slots;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpinRequest {
	private Long userId;
	private Long bet;
	private String slotType;
}
