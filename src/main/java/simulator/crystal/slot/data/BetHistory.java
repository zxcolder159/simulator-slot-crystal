package simulator.crystal.slot.data;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BetHistory {
	private Long userId;
	private String gameName;
	private Long bet;
	private Long win;
	private LocalDateTime time;
}
