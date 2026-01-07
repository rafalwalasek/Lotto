package pl.rafal.lotto.model.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Ticket {
    private int id;
    private List<Integer> numbers;
    private TicketStatus status;
}
