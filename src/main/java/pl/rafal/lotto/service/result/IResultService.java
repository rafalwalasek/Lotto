package pl.rafal.lotto.service.result;

import pl.rafal.lotto.model.draw.Draw;
import pl.rafal.lotto.model.result.Result;
import pl.rafal.lotto.model.ticket.Ticket;

import java.util.List;

public interface IResultService {
    List<Result> calculateResults(Draw draw, List<Ticket> tickets);
    Result getResultForTicket(int ticketId);
}
