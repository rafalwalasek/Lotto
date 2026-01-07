package pl.rafal.lotto.service.ticket;

import org.springframework.stereotype.Service;
import pl.rafal.lotto.model.ticket.Ticket;
import pl.rafal.lotto.model.ticket.TicketStatus;

import java.util.*;

@Service
public class TicketService implements ITicketService {
    private int nextId = 1;
    private Map<Integer, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket createTicket(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("Kupon musi zawierać dokładnie 6 liczb.");
        }

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException("Liczby w kuponie nie mogą się powtarzać.");
        }

        for (Integer n : numbers) {
            if (n < 1 || n > 49) {
                throw new IllegalArgumentException("Liczby muszą być w zakresie 1-49.");
            }
        }

        Ticket ticket = new Ticket();
        ticket.setId(nextId);
        ticket.setNumbers(numbers);
        ticket.setStatus(TicketStatus.NEW);

        tickets.put(nextId++, ticket);

        return ticket;
    }
    @Override
    public Ticket getTicketById(int id) {
        return null;
    }
    @Override
    public List<Ticket> getAllTickets() {
        return List.of();
    }
}
