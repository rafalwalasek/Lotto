package pl.rafal.lotto.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.rafal.lotto.model.Player;
import pl.rafal.lotto.model.Ticket;
import pl.rafal.lotto.repository.PlayerRepository;

import java.util.List;
import java.util.Set;

@Service
public class LottoService {
    private final PlayerRepository playerRepository;

    public LottoService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }



//    @Transactional
//    public void processDraw(Player player, Set<Integer> drawnNumbers) {
//        for (Ticket ticket : player.getTickets()) {
//            int hits = countHits(ticket.getNumbers(), drawnNumbers);
//            ticket.setHits(hits);
//
//            if (hits < 4) {
//                ticket.setExpired(true);
//                if (player.getBalance() != null && player.getBalance().getCount() > 0) {
//                    player.getBalance().setCount(player.getBalance().getCount() - 1);
//                }
//            }
//        }
//
//        playerRepository.save(player);
//    }
//    public int countHits(List<Integer> chosenNumbers, Set<Integer> drawnNumbers) {
//        int hits = 0;
//        for (Integer number : chosenNumbers) {
//            if (drawnNumbers.contains(number)) {
//                hits++;
//            }
//        }
//        return hits;
//    }
}
