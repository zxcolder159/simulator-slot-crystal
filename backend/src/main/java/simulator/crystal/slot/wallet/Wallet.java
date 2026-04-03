package simulator.crystal.slot.wallet;

import jakarta.persistence.*;
import lombok.*;
import simulator.crystal.slot.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
    
    private Long balance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
