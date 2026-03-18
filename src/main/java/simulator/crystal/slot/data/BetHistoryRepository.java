package simulator.crystal.slot.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BetHistoryRepository extends JpaRepository<BetHistory, Long> {
	List<BetHistory> findAllByUserIdOrderByTimeDesc(Long userId);
}
