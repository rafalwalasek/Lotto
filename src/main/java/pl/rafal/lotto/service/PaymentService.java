package pl.rafal.lotto.service;

import jakarta.transaction.Transactional;
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
    public String deposit(Player player, int amount, String method) {
        if (player.getBalance() == null) {
            Balance balance = new Balance();
            balance.setAmount(amount);
            balance.setPlayer(player);
            player.setBalance(balance);
        } else {
            Balance balance = player.getBalance();
            balance.setAmount(balance.getAmount() + amount);
        }

        playerRepository.save(player);

        return "Wpłacono " + amount + " zł metodą " + method;
    }
}
