package pl.rafal.lotto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.rafal.lotto.model.Balance;
import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.model.Ticket;
import pl.rafal.lotto.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CouponService {
    private final PlayerRepository playerRepository;

    public CouponService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void buyCoupon(Long playerId, int amount, List<Integer> chosenNumbers) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono gracza"));

        Ticket ticket = new Ticket();
        ticket.setNumbers(chosenNumbers);
        ticket.setExpired(false);
        ticket.setHits(0);

        if (player.getTickets() == null) {
            player.setTickets(new ArrayList<>());
        }
        player.getTickets().add(ticket);

        Balance balance = player.getBalance();
        if (balance == null) {
            balance = new Balance();
            balance.setAmount(0);
            balance.setCount(0);
            player.setBalance(balance);
        }

        if (balance.getAmount() < amount || balance.getCount() >= 2) {
            throw new RuntimeException("Nieprawidłowy zakup kuponu");
        }
        if (chosenNumbers.size() != 6) {
            throw new RuntimeException("Musisz wybrać dokładnie 6 liczb");
        }

        balance.setCount(balance.getCount() + 1);
        balance.setAmount(balance.getAmount() - amount);

        playerRepository.save(player);
    }
}
