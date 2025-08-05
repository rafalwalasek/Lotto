package pl.rafal.lotto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rafal.lotto.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByEmail(String email);
    Player findByEmail(String email);
}
