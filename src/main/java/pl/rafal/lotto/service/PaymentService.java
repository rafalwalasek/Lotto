package pl.rafal.lotto.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import pl.rafal.lotto.model.Balance;
import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.repository.PlayerRepository;

@Service
public class PaymentService {
    private final PlayerRepository playerRepository;

    public PaymentService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void deposit(Player player, int amount, String method) {
        if (player.getBalance() == null) {
            player.setBalance(new Balance(0, 0));
            player.getBalance().setPlayer(player);
        }

        player.getBalance().setAmount(player.getBalance().getAmount() + amount);

        playerRepository.save(player);
    }
}
