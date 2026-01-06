package pl.rafal.lotto.service.ticket;

import pl.rafal.lotto.model.ticket.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket createTicket(List<Integer> numbers);
    Ticket getTicketById(int id);
    List<Ticket> getAllTickets();
}
