package pl.rafal.lotto.dto.ticket;

import lombok.Data;

import java.util.List;

@Data
public class TicketRequestDTO {
    private List<Integer> numbers;
}
