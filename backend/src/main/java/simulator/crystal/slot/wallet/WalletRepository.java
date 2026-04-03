package simulator.crystal.slot.wallet;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select w from Wallet w where w.user.id = :userId")
	Optional<Wallet> findByUserIdForUpdate(Long userId);

	Optional<Wallet> findByUserId(Long userId);
}
