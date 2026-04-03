package simulator.crystal.slot.user;

import jakarta.persistence.*;
import lombok.*;
import simulator.crystal.slot.wallet.Wallet;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Wallet wallet;
    private String avatarUrl = "default-avatar.png";
	private String password;
	private String role;
}
