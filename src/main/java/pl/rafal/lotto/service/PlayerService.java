package pl.rafal.lotto.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import pl.rafal.lotto.model.Balance;
import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.repository.PlayerRepository;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void registerPlayer(Player player) {
        if (playerRepository.existsByEmail(player.getEmail())) {
            throw new RuntimeException("Użytkownik z takim e-mailem już istnieje.");
        }

        Balance balance = new Balance(0, 0);
        balance.setPlayer(player);
        player.setBalance(balance);

        playerRepository.save(player);
    }
    @Transactional(readOnly = true)
    public Player login(String email, String password) {
        Player dbPlayer = playerRepository.findByEmail(email);

        if (dbPlayer == null || !dbPlayer.getPassword().equals(password)) {
            throw new RuntimeException("Nieprawidłowy email lub hasło!");
        }

        return dbPlayer;
    }
}
